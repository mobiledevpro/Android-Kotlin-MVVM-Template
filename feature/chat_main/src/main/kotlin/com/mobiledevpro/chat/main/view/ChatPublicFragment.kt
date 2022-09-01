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
import com.mobiledevpro.app.ui.mainscreen.view.AppbarAnimation
import com.mobiledevpro.chat.main.R
import com.mobiledevpro.chat.main.databinding.FragmentChatPublicBinding
import com.mobiledevpro.chat.main.di.featureChatMainModule
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
import com.mobiledevpro.common.ui.extension.observe
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.android.scope.getOrCreateScope
import org.koin.androidx.scope.currentScope
import org.koin.androidx.scope.fragmentScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
import com.mobiledevpro.app.R as RApp
import com.mobiledevpro.navigation.R as RNav


/**
 * Main fragment for main activity
 *
 *
 * www.mobile-dev.pro
 */

class ChatPublicFragment : BaseFragment<FragmentChatPublicBinding>(
    layoutId = R.layout.fragment_chat_public,
    FragmentSettings(
        statusBarColor = RApp.color.colorBlackTransparent,//RApp.color.colorWindowGreyBackground,
        appBarColor = RApp.color.colorWindowGreyBackground,
        appBarTitle = RApp.string.app_title_chat_public,
        appBarTitleColor = RApp.color.colorTextPrimary,
        appWindowBackground = RApp.drawable.background_window_dark_solid,
        homeIconId = RApp.drawable.ic_list_dark_24dp,
        optionsMenuId = R.menu.menu_chat_public,
        homeIconBackPressEnabled = false,
        exitTransition = RNav.transition.fade
    )
), AndroidScopeComponent {

    override val scope: Scope by fragmentScope()

    private val viewModel: ChatPublicViewModel by inject(mode = LazyThreadSafetyMode.NONE)

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
            RNav.id.menu_action_settings -> {
                // Toast.makeText(requireActivity(), "Settings. Not implemented yet", Toast.LENGTH_SHORT).show()
              /*  Navigation(NavigateTo.PROFILE_SETTINGS)
                    .let(this::launch)

               */

                (requireActivity() as AppbarAnimation).onAppbarExpand()
                true

            }
            else -> super.onOptionsItemSelected(item)
        }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun addImeListener(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->

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
