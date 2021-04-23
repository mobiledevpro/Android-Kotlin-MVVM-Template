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
package com.mobiledevpro.common.ui.lifecycle

import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *  Observer to request and handle Runtime permissions
 *
 * Created on Apr 23, 2021.
 *
 */
class RuntimePermissionObserver(
    private val activity: FragmentActivity
) : LifecycleObserver {

    private var onGranted: () -> Unit = {}
    private var onDenied: () -> Unit = {}
    private var onShouldShowRationale: () -> Unit = {}

    private lateinit var launcher: ActivityResultLauncher<Array<String>>

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        launcher = activity
            .activityResultRegistry
            .register(this.javaClass.name, ActivityResultContracts.RequestMultiplePermissions()) {

                var isGranted = false

                for (result in it) {
                    isGranted = result.value
                }

                if (isGranted)
                    onGranted()
                else
                    onDenied()

            }
    }

    fun launch(
        permissions: Array<String>,
        onGranted: () -> Unit = {},
        onDenied: () -> Unit = {},
        onShouldShowRationale: () -> Unit = {}
    ) {
        this.onGranted = onGranted
        this.onDenied = onDenied
        this.onShouldShowRationale = onShouldShowRationale

        when {
            // You can use the API that requires the permission.
            permissions.checkSelfPermission() -> onGranted()
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            permissions.checkShouldShowRationale(activity) -> onShouldShowRationale()

            else -> launcher.launch(permissions)
        }
    }

    private fun Array<String>.checkSelfPermission(): Boolean {

        var isGranted = false

        for (permission in this) {

            isGranted = ContextCompat.checkSelfPermission(
                activity,
                permission
            ) == PackageManager.PERMISSION_GRANTED

            //if at least one permission is not granted, stop checking
            if (!isGranted) break
        }

        return isGranted
    }

    private fun Array<String>.checkShouldShowRationale(
        activity: FragmentActivity
    ): Boolean {

        var isShouldShow = false

        for (permission in this) {
            isShouldShow = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)

            //if at least one permission should be rationale, stop checking
            if (isShouldShow) break
        }

        return isShouldShow
    }
}