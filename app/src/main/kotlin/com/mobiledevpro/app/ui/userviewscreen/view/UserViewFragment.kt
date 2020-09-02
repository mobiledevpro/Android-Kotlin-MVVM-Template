package com.mobiledevpro.app.ui.userviewscreen.view

import androidx.fragment.app.viewModels
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentUserViewBinding
import com.mobiledevpro.app.ui.userviewscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.common.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * Fragment for UserView screen
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
@AndroidEntryPoint
class UserViewFragment : BaseFragment<FragmentUserViewBinding>(
    layoutId = R.layout.fragment_user_view,
    appBarTitle = R.string.appbar_title_edit_view,
    homeIconId = R.drawable.ic_arrow_back_white_24dp,
    appBarColor = 0,
    appBarSubTitle = 0,
    statusBarColor = 0,
    optionsMenuId = 0
) {

    private val viewModel: UserDataViewModel by viewModels()

    override fun onInitDataBinding() {
        viewBinding.model = viewModel
        lifecycle.addObserver(viewModel)
    }

    override fun observeLifecycleEvents() {

    }

}