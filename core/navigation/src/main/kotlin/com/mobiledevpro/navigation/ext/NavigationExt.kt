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
package com.mobiledevpro.navigation.ext

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mobiledevpro.navigation.NavigateTo
import com.mobiledevpro.navigation.Navigation
import com.mobiledevpro.navigation.R


fun Fragment.launch(navigation: Navigation) {
    if (navigation.to == NavigateTo.BACK) {
        requireActivity().onBackPressed()
        return
    }

    findNavController().launch(navigation)
}

fun Activity.launch(@IdRes navHostViewId: Int, navigation: Navigation) {
    if (navigation.to == NavigateTo.BACK) {
        onBackPressed()
        return
    }

    findNavController(this, navHostViewId)
        .launch(navigation)
}

private fun NavController.launch(navigation: Navigation) {
    val commonNavOptionsBuilder = NavOptions.Builder()

    val navResId = when (navigation.to) {
        NavigateTo.CHAT_MAIN -> R.id.actionNavToChatMain
        NavigateTo.PROFILE_SETTINGS -> R.id.actionNavToProfileSettings
        else -> throw RuntimeException("Navigation not found. Please check NavigationExt")
    }

    if (navResId > 0)
        navigate(
            navResId,
            navigation.extras,
            commonNavOptionsBuilder.build()
        )
}