package com.mobiledevpro.app.ui.usereditscreen.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobiledevpro.app.R
import com.mobiledevpro.app.ViewModelFactory
import com.mobiledevpro.app.databinding.FragmentUserEditBinding
import com.mobiledevpro.app.helper.showViewUserFragment
import com.mobiledevpro.app.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.commons.fragment.BaseFragment

/**
 * Fragment for UserEdit screen
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class UserEditFragment : BaseFragment() {

    private lateinit var userViewModel: UserDataViewModel

    override fun getLayoutResId() = R.layout.fragment_user_edit

    override fun getAppBarTitle() = R.string.appbar_title_edit_user

    override fun getHomeAsUpIndicatorIcon() = R.drawable.ic_arrow_back_white_24dp

    override fun populateView(view: View, bundle: Bundle?): View {
        //databinding
        val binding = FragmentUserEditBinding.bind(view)
                .apply {
                    userDataModel = userViewModel
                }
        binding.lifecycleOwner = viewLifecycleOwner

        observeEvents(view)

        return binding.root
    }

    override fun initPresenters() {
        val app = requireActivity().application
        val viewModelFactory = ViewModelFactory(app)

        //init ViewModel for this fragment
        userViewModel = ViewModelProvider(activity as FragmentActivity, viewModelFactory)
                .get(UserDataViewModel::class.java)

        //add lifecycle observer to viewmodel
        lifecycle.addObserver(userViewModel)
    }

    private fun observeEvents(view: View) {
        //show toasts
        userViewModel.showToastEvent.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { txt ->
                Toast.makeText(context, txt, Toast.LENGTH_SHORT).show()
            }
        })

        //navigate to user view fragment
        userViewModel.navigateToUserView.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { b ->
                if (b)
                    view.showViewUserFragment()
            }
        })
    }

}