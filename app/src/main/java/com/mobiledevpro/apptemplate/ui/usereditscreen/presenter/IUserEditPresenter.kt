package com.mobiledevpro.apptemplate.ui.usereditscreen.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.mobiledevpro.database.model.User

/**
 * Presenter for User edit screen
 *
 * Created by Dmitriy Chernysh on 12/18/19.
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
internal interface IUserEditPresenter : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()

    fun saveUserData(user: User)
}