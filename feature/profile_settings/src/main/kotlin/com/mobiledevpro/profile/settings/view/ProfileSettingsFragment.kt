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

import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
import com.mobiledevpro.profile.settings.R
import com.mobiledevpro.profile.settings.databinding.FragmentProfileSettingsBinding
import com.mobiledevpro.profile.settings.di.featureProfileSettingsModule
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
import com.mobiledevpro.app.R as RApp

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
        appWindowBackground = RApp.drawable.background_window_light,
        homeIconId = RApp.drawable.ic_back_arrow_light_24dp,
        homeIconBackPressEnabled = true,
        enterTransition = RApp.transition.slide_right
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


}