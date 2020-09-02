package com.mobiledevpro.app.ui.userviewscreen.view

import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentUserViewBinding
import com.mobiledevpro.app.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.common.ui.base.BaseFragment
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
class UserViewFragment : BaseFragment<FragmentUserViewBinding>(
    layoutId = R.layout.fragment_user_view,
    appBarTitle = R.string.appbar_title_edit_view,
    homeIconId = R.drawable.ic_arrow_back_white_24dp
) {

    private val userViewModel: UserDataViewModel by viewModel()

    override fun onInitDataBinding() {
        viewBinding.userDataModel = userViewModel
        lifecycle.addObserver(userViewModel)
    }

    override fun observeLifecycleEvents() {

    }

}