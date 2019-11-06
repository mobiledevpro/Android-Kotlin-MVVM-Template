package com.mobiledevpro.apptemplate.ui.mainscreen.presenter

import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Interface for main screen
 *
 * Created by Dmitriy V. Chernysh
 * dmitriy.chernysh@gmail.com
 *
 * https://instagr.am/mobiledevpro/
 *
 * #MobileDevPro
 */

interface IMain {
    interface View {
        fun showToast(@StringRes msg: Int)
    }

    interface Presenter : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStartView()

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStopView()
    }
}
