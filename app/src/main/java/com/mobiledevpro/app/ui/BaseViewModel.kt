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

    private var disposable: CompositeDisposable = CompositeDisposable()

    val subscriptions: CompositeDisposable
        get() {
            if (disposable.isDisposed) disposable = CompositeDisposable()
            return disposable
        }

    fun clearSubscriptions() {
        disposable.dispose()
    }

    override fun onCleared() {
        disposable.dispose()
    }
}