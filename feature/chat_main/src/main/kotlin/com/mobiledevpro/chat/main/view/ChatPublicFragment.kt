package com.mobiledevpro.chat.main.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets.Type.ime
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import com.mobiledevpro.chat.main.R
import com.mobiledevpro.chat.main.databinding.FragmentChatPublicBinding
import com.mobiledevpro.chat.main.di.featureChatMainModule
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
import com.mobiledevpro.common.ui.extension.observe
import com.mobiledevpro.navigation.NavigateTo
import com.mobiledevpro.navigation.Navigation
import com.mobiledevpro.navigation.ext.launch
import org.koin.android.scope.getOrCreateScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
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
        homeIconBackPressEnabled = false,
        exitTransition = RApp.transition.slide_left
    )
), KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    private val viewModel: ChatPublicViewModel
            by lazy(LazyThreadSafetyMode.NONE) { scope.get() }

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            addImeListener(view)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(
                    requireActivity(),
                    "Chats list. Not implemented yet",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            R.id.menu_action_settings -> {
                // Toast.makeText(requireActivity(), "Settings. Not implemented yet", Toast.LENGTH_SHORT).show()
                Navigation(NavigateTo.PROFILE_SETTINGS)
                    .let(this::launch)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun addImeListener(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->

            try {
                val imeVisible = insets.toWindowInsets()?.isVisible(ime()) ?: false
                //  val imeHeight = insets.getInsets(Type.ime()).bottom
                Log.d(this::class.java.name, "imeVisible: $imeVisible")
                if (imeVisible)
                    viewBinding.rvMessageList.apply {
                        smoothScrollToPosition(this.adapter?.itemCount?.minus(1) ?: 0)
                    }
            } catch (e: NoClassDefFoundError) {

            }
            insets
        }
    }


}
