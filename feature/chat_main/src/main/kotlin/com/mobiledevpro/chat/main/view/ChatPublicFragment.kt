package com.mobiledevpro.chat.main.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets.Type.ime
import android.widget.Toast
import androidx.core.view.ViewCompat
import com.mobiledevpro.app.helper.showProfileSettingsFragment
import com.mobiledevpro.chat.main.R
import com.mobiledevpro.chat.main.databinding.FragmentChatPublicBinding
import com.mobiledevpro.chat.main.di.featureChatMainModule
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
import com.mobiledevpro.common.ui.extension.observe
import org.koin.androidx.scope.fragmentScope
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
        appWindowBackground = RApp.drawable.background_window_dark,
        homeIconId = RApp.drawable.ic_list_dark_24dp,
        optionsMenuId = R.menu.menu_chat_public,
        homeIconBackPressEnabled = false
    )
) {

    private val viewModel: ChatPublicViewModel by lazy { fragmentScope().get() }

    init {
        loadKoinModules(featureChatMainModule)
    }

    override fun onInitDataBinding() {
        viewBinding.model = viewModel
        lifecycle.addObserver(viewModel)
    }

    override fun observeLifecycleEvents() {

        observe(viewModel.errorMessage, observer = {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->

            try {
                //  val imeVisible = insets.isVisible(Type.ime())
                val imeVisible = insets.toWindowInsets()?.isVisible(ime()) ?: false
                //  val imeHeight = insets.getInsets(Type.ime()).bottom
                Log.d(this::class.java.name, "imeVisible: $imeVisible")
                /* if (imeVisible)
                 viewBinding.rvMessages.smoothScrollToPosition(
                 )*/
            } catch (e: NoClassDefFoundError) {

            }
            insets
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(requireActivity(), "Chats list. Not implemented yet", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_action_settings -> {
                // Toast.makeText(requireActivity(), "Settings. Not implemented yet", Toast.LENGTH_SHORT).show()
                showProfileSettingsFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


}
