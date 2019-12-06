package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ViewModelFactory
import com.mobiledevpro.apptemplate.databinding.FragmentUserEditBinding
import com.mobiledevpro.apptemplate.helper.showViewUserFragment
import com.mobiledevpro.apptemplate.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.commons.fragment.BaseFragment

/**
 * Fragment for UserEdit screen
 *
 *
 * Created by Dmitriy Chernysh on 11/7/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class UserEditFragment : BaseFragment() {

    private lateinit var userViewModel: UserDataViewModel
    private lateinit var binding: FragmentUserEditBinding

    override fun getLayoutResId() = R.layout.fragment_user_edit

    override fun populateView(view: View, bundle: Bundle?): View {
        //databinding
        binding = FragmentUserEditBinding.bind(view)
                .apply {
                    userDataModel = userViewModel
                }
        binding.lifecycleOwner = viewLifecycleOwner

        observeEvents(view)

        return binding.root
    }

    override fun initPresenters() {
        //init view model instead of presenter
        val app = requireNotNull(activity).application
        val viewModelFactory = ViewModelFactory(app)

        //init ViewModel for this fragment
        userViewModel = ViewModelProvider(activity as FragmentActivity, viewModelFactory)
                .get(UserDataViewModel::class.java)
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
                    showViewUserFragment(view)
            }
        })
    }

}