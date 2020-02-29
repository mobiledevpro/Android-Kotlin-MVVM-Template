package com.mobiledevpro.app.ui.userviewscreen.view

import android.os.Bundle
import android.view.View
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentUserViewBinding
import com.mobiledevpro.app.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.commons.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for UserView screen
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class UserViewFragment : BaseFragment() {

    private val userViewModel: UserDataViewModel by viewModel()

    override fun getLayoutResId() = R.layout.fragment_user_view

    override fun getAppBarTitle() = R.string.appbar_title_edit_view

    override fun getHomeAsUpIndicatorIcon() = R.drawable.ic_arrow_back_white_24dp

    override fun populateView(view: View, bundle: Bundle?): View {
        val binding = FragmentUserViewBinding.bind(view)
                .apply {
                    userDataModel = userViewModel
                }
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }

    override fun initPresenters() {
        //add lifecycle observer to presenter
        lifecycle.addObserver(userViewModel)
    }

}