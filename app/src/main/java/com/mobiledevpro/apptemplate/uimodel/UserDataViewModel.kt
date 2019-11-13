package com.mobiledevpro.apptemplate.uimodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mobiledevpro.data.LOG_TAG_DEBUG
import com.mobiledevpro.data.LOG_TAG_ERROR
import com.mobiledevpro.interactor.useredit.IUserEditInteractor
import com.mobiledevpro.interactor.useredit.UserEditInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

/**
 * ViewModel for user data
 *
 *
 * Created by Dmitriy Chernysh on 11/8/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class UserDataViewModel(app: Application) : AndroidViewModel(app) {

    val userName = MutableLiveData<String>()
    val userAge = MutableLiveData<String>()

    private var interactor: IUserEditInteractor
    private var subscriptions = CompositeDisposable()

    init {
        Log.d(LOG_TAG_DEBUG, "UserDataViewModel created!")
        //init all the data here

        interactor = UserEditInteractor(app)

        val dispos: Disposable = interactor.getUserObservable()
                .subscribeBy { user ->
                    userName.value = user.name
                    userAge.value = if (user.age > 0) user.age.toString() else ""
                }
        subscriptions.add(dispos)

    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()

        Log.d(LOG_TAG_DEBUG, "UserDataViewModel cleared!")
    }


    fun updateUser() {
        val disposable: Disposable =
                interactor.updateUser(userName.value ?: "", userAge.value?.toInt() ?: 0)
                        .subscribeBy(
                                onSuccess = {
                                    Log.d(LOG_TAG_ERROR, "Saved!")
                                },
                                onError = {
                                    Log.e(LOG_TAG_ERROR, it.localizedMessage)
                                })
        subscriptions.add(disposable)

    }
}
