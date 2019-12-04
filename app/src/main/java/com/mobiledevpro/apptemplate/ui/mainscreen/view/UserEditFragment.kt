package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ViewModelFactory
import com.mobiledevpro.apptemplate.databinding.FragmentUserEditBinding
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

    companion object {
        fun newInstance(): UserEditFragment {

            val args = Bundle()

            val fragment = UserEditFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_user_edit

    override fun populateView(view: View, bundle: Bundle?): View {
        //databinding
        binding = FragmentUserEditBinding.bind(view)
                .apply {
                    userDataModel = userViewModel
                }
        binding.lifecycleOwner = viewLifecycleOwner
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
}