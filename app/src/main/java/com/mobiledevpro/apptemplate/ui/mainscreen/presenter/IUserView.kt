package com.mobiledevpro.apptemplate.ui.mainscreen.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

/**
 * Interface for UserView screen
 *
 *
 * Created by Dmitriy Chernysh on 11/7/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
interface IUserView {
    interface View

    interface Presenter {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStartView()

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStopView()
    }
}
