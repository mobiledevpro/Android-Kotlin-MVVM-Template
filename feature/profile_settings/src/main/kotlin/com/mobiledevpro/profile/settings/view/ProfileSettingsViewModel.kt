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

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.mobiledevpro.app.BuildConfig
import com.mobiledevpro.app.helper.ResourcesProvider
import com.mobiledevpro.common.ui.base.BaseViewModel
import com.mobiledevpro.app.R as RApp

/**
 * View model for Profile Settings screen
 *
 * Created on Jan 26, 2021.
 *
 */

class ProfileSettingsViewModel(
    private val resourcesProvider: ResourcesProvider
) : BaseViewModel() {

    private val _appVersion = MutableLiveData<String>()
    val appVersion: LiveData<String> = _appVersion

    init {
        initAppVersion()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartView() {
        //do something on start view if it's needed
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopView() {
        //do something on stop view if it's needed
    }

    private fun initAppVersion() {
        resourcesProvider.getFormattedString(RApp.string.app_version, BuildConfig.VERSION_NAME)
            .let(_appVersion::postValue)
    }
}