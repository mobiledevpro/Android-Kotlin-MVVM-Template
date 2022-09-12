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

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

interface BaseActivityInterface {
    fun setAppBarTitle(titleString: String)

    fun setAppBarSubTitle(subTitleString: String)

    fun setStatusBarColor(@ColorRes colorResId: Int)

    fun setAppBarColor(@ColorRes colorResId: Int)

    fun setAppBarTitleColor(@ColorRes colorResId: Int)

    fun setAppWindowBackground(@DrawableRes backgroundResId: Int)

    fun setHomeAsUpIndicatorIcon(@DrawableRes drawable: Int)
}