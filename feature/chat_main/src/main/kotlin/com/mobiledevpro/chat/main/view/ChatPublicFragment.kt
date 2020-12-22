package com.mobiledevpro.chat.main.view

import android.view.MenuItem
import android.widget.Toast
import com.mobiledevpro.chat.main.R
import com.mobiledevpro.chat.main.databinding.FragmentChatPublicBinding
import com.mobiledevpro.chat.main.di.featureChatMainModule
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
import com.mobiledevpro.common.ui.extension.observe
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.getViewModel
import org.koin.core.context.loadKoinModules
import com.mobiledevpro.app.R as RApp


/**
 * Main fragment for main activity
 *
 *
 * www.mobile-dev.pro
 */

class ChatPublicFragment : BaseFragment<FragmentChatPublicBinding>(
    layoutId = R.layout.fragment_chat_public,
    FragmentSettings(
        statusBarColor = RApp.color.colorWindowGreyBackground,
        appBarColor = RApp.color.colorWindowGreyBackground,
        appBarTitle = RApp.string.app_title_chat_public,
        appBarTitleColor = RApp.color.colorTextPrimary,
        homeIconId = RApp.drawable.ic_list_dark_24dp,
        optionsMenuId = R.menu.menu_chat_public,
        homeIconBackPressEnabled = false
    )
) {

    init {
        loadKoinModules(featureChatMainModule)
    }

    private lateinit var viewModel: ChatPublicViewModel

    override fun onInitDataBinding() {
        viewModel = lifecycleScope.getViewModel(this)

        viewBinding.model = viewModel
        //add lifecycle observer to viewmodel
        lifecycle.addObserver(viewModel)
    }

    override fun observeLifecycleEvents() {

        observe(viewModel.errorMessage, observer = {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show();
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(requireActivity(), "Chats list. Not implemented yet", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_action_settings -> {
                Toast.makeText(requireActivity(), "Settings. Not implemented yet", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


}
