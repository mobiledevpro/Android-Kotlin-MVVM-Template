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
package com.mobiledevpro.app.helper

import androidx.annotation.StringRes

/**
 *  Provider for app resources (as example, from strings.xml)
 *
 * Created on Dec 15, 2020.
 *
 */
interface ResourcesProvider {
    fun getErrorMessage(throwable: Throwable?): String

    fun getStringMessage(@StringRes resId: Int): String

    fun getFormattedString(@StringRes resId: Int, vararg args: Any): String
}