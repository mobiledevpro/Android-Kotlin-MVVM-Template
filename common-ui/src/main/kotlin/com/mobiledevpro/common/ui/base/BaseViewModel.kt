/*
 * Copyright 2019 http://mobile-dev.pro
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
 */

package com.mobiledevpro.common.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Base class for ViewModels
 *
 * http://mobile-dev.pro
 *
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private var disposable: CompositeDisposable = CompositeDisposable()

    val subscriptions: CompositeDisposable
        get() {
            if (disposable.isDisposed) disposable = CompositeDisposable()
            return disposable
        }

    fun clearSubscriptions() {
        disposable.dispose()
    }

    override fun onCleared() {
        disposable.dispose()
    }
}