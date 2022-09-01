/*
 * Copyright 2021 | Dmitri Chernysh | http://mobile-dev.pro
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
package com.mobiledevpro.app.ui.mainscreen.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.mobiledevpro.app.ui.mainscreen.domain.AppbarState
import com.mobiledevpro.common.ui.base.BaseViewModel
import com.mobiledevpro.common.ui.livedata.Event
import com.mobiledevpro.common.ui.livedata.SingleLiveData

/**
 * View model for AppBar changes
 *
 * Created on Dec 01, 2021.
 *
 */

class AppbarViewModel : BaseViewModel() {

    val eventAppbarState = SingleLiveData<AppbarState>()


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartView() {
        //do something on start view if it's needed
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopView() {
        //do something on stop view if it's needed
    }
}