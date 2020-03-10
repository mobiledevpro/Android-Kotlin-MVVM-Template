package com.mobiledevpro.app.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Base class for ViewModels
 *
 * Created by Dmitriy Chernysh on Mar 11, 2020.
 *
 * http://androiddev.pro
 *
 */
abstract class BaseViewModel : ViewModel() {

    private var subscriptions: CompositeDisposable = CompositeDisposable()

    fun getSubscriber(): CompositeDisposable {
        if (subscriptions.isDisposed) subscriptions = CompositeDisposable()
        return subscriptions
    }

    fun clearSubscriptions() {
        subscriptions.dispose()
    }

    override fun onCleared() {
        subscriptions.dispose()
    }
}