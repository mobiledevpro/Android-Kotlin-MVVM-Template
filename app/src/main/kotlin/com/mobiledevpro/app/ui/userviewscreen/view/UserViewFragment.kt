package com.mobiledevpro.app.ui.userviewscreen.view

import androidx.fragment.app.viewModels
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentUserViewBinding
import com.mobiledevpro.app.ui.userviewscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
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
    FragmentSettings(
        appBarTitle = R.string.appbar_title_edit_view,
        homeIconId = R.drawable.ic_arrow_back_white_24dp
    )
) {

    private val viewModel: UserDataViewModel by viewModel()

    override fun onInitDataBinding() {
        viewBinding.model = viewModel
        lifecycle.addObserver(viewModel)
    }

    override fun observeLifecycleEvents() {

    }

}