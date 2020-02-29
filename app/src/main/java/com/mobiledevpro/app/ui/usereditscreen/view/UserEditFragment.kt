package com.mobiledevpro.app.ui.usereditscreen.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentUserEditBinding
import com.mobiledevpro.app.helper.showViewUserFragment
import com.mobiledevpro.app.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.commons.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

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

    //init ViewModel using Koin
    private val userViewModel: UserDataViewModel by viewModel()

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