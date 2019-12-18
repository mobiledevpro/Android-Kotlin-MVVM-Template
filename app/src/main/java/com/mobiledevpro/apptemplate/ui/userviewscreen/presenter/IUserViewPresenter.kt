package com.mobiledevpro.apptemplate.ui.userviewscreen.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Presenter for User view screen
 *
 * Created by Dmitriy Chernysh on 12/18/19.
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
internal interface IUserViewPresenter : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()
}