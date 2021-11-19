/*
 * Copyright 2020 | Dmitri Chernysh | http://mobile-dev.pro
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.mobiledevpro.profile.settings.view

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
import com.mobiledevpro.profile.settings.R
import com.mobiledevpro.profile.settings.databinding.FragmentProfileSettingsBinding
import com.mobiledevpro.profile.settings.di.featureProfileSettingsModule
import com.mobiledevpro.utils.LOG_TAG_DEBUG
import com.mobiledevpro.utils.LOG_TAG_ERROR
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
import com.mobiledevpro.app.R as RApp
import com.mobiledevpro.navigation.R as RNav

/**
 * Profile Settings screen
 *
 * Created on Jan 26, 2021.
 *
 */
class ProfileSettingsFragment : BaseFragment<FragmentProfileSettingsBinding>(
    layoutId = R.layout.fragment_profile_settings,
    FragmentSettings(
        statusBarColor = RApp.color.colorPrimary,
        appBarColor = RApp.color.colorPrimary,
        appBarTitle = 0,
        appBarTitleColor = RApp.color.colorWindowGreyBackground,
        appWindowBackground = RApp.drawable.background_window_light_solid,
        homeIconId = RApp.drawable.ic_back_arrow_light_24dp,
        homeIconBackPressEnabled = true,
        enterTransition = RNav.transition.slide_down
    )
), KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    private val viewModel: ProfileSettingsViewModel
            by lazy(LazyThreadSafetyMode.NONE) { scope.get() }

    init {
        loadKoinModules(featureProfileSettingsModule)
    }

    override fun onInitDataBinding() {
        viewBinding.model = viewModel
        lifecycle.addObserver(viewModel)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Listen for a soft keyboard visibility change
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            addKeyboardListener(view)
        else
            addKeyboardListenerCompat(view)

    }

    override fun observeLifecycleEvents() {
        /*  observe(viewModel.appbarTitle, observer = { title ->
              (requireActivity() is BaseActivityInterface).apply {
                  (requireActivity() as BaseActivityInterface).setAppBarTitle(title)
              }
          })

          observe(viewModel.eventNavigateTo, observer = { navigation ->
              try {
                  openScreen(navigation)
              } catch (e: RuntimeException) {
                  val err = e.localizedMessage
                  if (!err.isNullOrEmpty())
                      showErrorDialog(err)
              }
          })

         */
    }


    @RequiresApi(Build.VERSION_CODES.R)
    private fun addKeyboardListener(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
            try {
                val imeVisible =
                    insets.toWindowInsets()?.isVisible(WindowInsets.Type.ime()) ?: false
                //  val imeHeight = insets.getInsets(Type.ime()).bottom
                Log.d(this::class.java.name, "imeVisible: $imeVisible")
                viewBinding.layoutProfile.apply {
                    if (imeVisible) transitionToEnd()
                    else transitionToStart()
                }
            } catch (e: NoClassDefFoundError) {
                Log.e(LOG_TAG_ERROR, "addKeyboardListener: ${e.localizedMessage}", e)
            }
            insets
        }
    }

    /**
     * NOTE: This only works when android:windowSoftInputMode is set to adjustResize in the manifest for an Activity
     */
    private fun addKeyboardListenerCompat(view: View) {
        view.viewTreeObserver.addOnGlobalLayoutListener {

            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)

            val rootHeight = view.rootView.height
            val visibleHeight = rect.height()

            Log.d(LOG_TAG_DEBUG, "addKeyboardListenerOld: rootView.height  = $rootHeight")
            Log.d(LOG_TAG_DEBUG, "addKeyboardListenerOld: visible.height  = $visibleHeight")
            val heightDiff: Int = rootHeight - visibleHeight

            val keyboardVisible: Boolean = heightDiff > 500

            Log.d(
                LOG_TAG_DEBUG,
                "addKeyboardListenerOld: heightDiff = $heightDiff"
            )

            viewBinding.layoutProfile.apply {
                if (keyboardVisible) transitionToEnd()
                else transitionToStart()
            }
        }
    }
}