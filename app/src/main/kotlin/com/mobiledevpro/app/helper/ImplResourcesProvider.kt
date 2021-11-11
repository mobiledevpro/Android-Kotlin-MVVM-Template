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

import android.content.res.Resources
import com.mobiledevpro.app.R
import com.mobiledevpro.errors.NetworkConnectionThrowable

/**
 * Provider for app resources (as example, from strings.xml)
 *
 * Created on Dec 15, 2020.
 *
 */
class ImplResourcesProvider(
    private val resources: Resources
) : ResourcesProvider {

    override fun getErrorMessage(throwable: Throwable?): String =
        when (throwable) {

            is NetworkConnectionThrowable ->
                resources.getString(R.string.message_trouble_internet_connection)

            else -> throwable?.localizedMessage ?: ""
        }


    override fun getStringMessage(resId: Int): String =
        resources.getString(resId)

    override fun getFormattedString(resId: Int, vararg args: Any): String =
        resources.getString(resId, *args)
}