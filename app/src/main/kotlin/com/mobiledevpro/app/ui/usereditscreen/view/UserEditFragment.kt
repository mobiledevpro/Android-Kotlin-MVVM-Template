package com.mobiledevpro.app.ui.usereditscreen.view

import android.widget.Toast
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentUserEditBinding
import com.mobiledevpro.app.helper.showViewUserFragment
import com.mobiledevpro.app.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.extension.observe
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
class UserEditFragment : BaseFragment<FragmentUserEditBinding>(
    layoutId = R.layout.fragment_user_edit,
    homeIconId = R.drawable.ic_arrow_back_white_24dp,
    appBarTitle = R.string.appbar_title_edit_user
) {

    //init ViewModel using Koin
    private val userViewModel: UserDataViewModel by viewModel()

    override fun onInitDataBinding() {
        viewBinding.userDataModel = userViewModel
        lifecycle.addObserver(userViewModel)
    }

    override fun observeLifecycleEvents() {
        //show toasts
        observe(userViewModel.showToastEvent, observer = {
            it.getContentIfNotHandled()?.let { txt ->
                Toast.makeText(context, txt, Toast.LENGTH_SHORT).show()
            }
        })

        //navigate to user view fragment
        observe(userViewModel.navigateToUserView, observer = {
            it.getContentIfNotHandled()?.let { b ->
                if (b)
                    view?.showViewUserFragment()
            }
        })
    }

}