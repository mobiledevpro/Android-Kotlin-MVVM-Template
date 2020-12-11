package com.mobiledevpro.app.ui.mainscreen.view

import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentChatPublicBinding
import com.mobiledevpro.app.ui.mainscreen.viewmodel.ChatPublicViewModel
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Main fragment for main activity
 *
 *
 * Created by Dmitriy Chernysh
 *
 * #MobileDevPro
 */

class ChatPublicFragment : BaseFragment<FragmentChatPublicBinding>(
    layoutId = R.layout.fragment_chat_public,
    FragmentSettings(
        statusBarColor = R.color.colorWindowGreyBackground,
        appBarTitle = R.string.app_title_chat_public
    )
) {

     private val viewModel: ChatPublicViewModel by viewModel()

    override fun onInitDataBinding() {
        viewBinding.model = viewModel
        //add lifecycle observer to viewmodel
        lifecycle.addObserver(viewModel)
    }

    override fun observeLifecycleEvents() {

        /*observe(viewModel.editUserButton, observer = {
           // if (it) view?.showEditUserFragment()
        })*/
    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.menu_action_crash -> {
                throw RuntimeException("Test crash")
            }
            else -> super.onOptionsItemSelected(item)
        }

     */
}
