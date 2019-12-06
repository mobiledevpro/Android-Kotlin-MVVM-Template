package com.mobiledevpro.apptemplate.ui.mainscreen.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mobiledevpro.apptemplate.Event
import com.mobiledevpro.data.LOG_TAG_DEBUG
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

    private val _cachedUserName = MutableLiveData<String>()
    private val _cachedUserAge = MutableLiveData<String>()

    val userName = MutableLiveData<String>()
    val userAge = MutableLiveData<String>()

    private val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>> = _showToastEvent

    private var interactor: IUserEditInteractor
    private var subscriptions = CompositeDisposable()

    /**
     * LifeCycle-aware
     */
    init {
        Log.d(LOG_TAG_DEBUG, "UserDataViewModel created!")
        //init all the data here

        interactor = UserEditInteractor(app)

        //observe user data changes
        observeUserData()
    }

    /**
     * LifeCycle-aware
     */
    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()

        Log.d(LOG_TAG_DEBUG, "UserDataViewModel cleared!")
    }


    /**
     * It calls from xml layout
     */
    fun updateUser() {
        val disposable: Disposable =
                interactor.updateUser(userName.value ?: "", userAge.value?.toInt() ?: 0)
                        .subscribeBy(
                                onSuccess = {
                                    Log.d(LOG_TAG_DEBUG, "Saved!")
                                    showToastMessage("Saved!")
                                },
                                onError = {
                                    Log.e(LOG_TAG_DEBUG, it.localizedMessage ?: "Something wrong")
                                    showToastMessage("Error: " + it.localizedMessage)
                                })
        subscriptions.add(disposable)

    }

    /**
     * It calls from xml layout
     */
    fun isUpdateButtonEnabled(): LiveData<Boolean> {
        val isEnabled = MediatorLiveData<Boolean>()

        isEnabled.addSource(userName) {
            isEnabled.value = isUserDataChanged()
        }

        isEnabled.addSource(userAge) {
            isEnabled.value = isUserDataChanged()
        }

        return isEnabled

    }

    private fun isUserDataChanged(): Boolean {
        return (userName.value != _cachedUserName.value) ||
                (userAge.value != _cachedUserAge.value)
    }

    private fun showToastMessage(message: String) {
        _showToastEvent.value = Event(message)
    }

    private fun observeUserData() {
        val dispos: Disposable = interactor.getUserObservable()
                .subscribeBy { user ->
                    userName.value = user.name
                    userAge.value = if (user.age > 0) user.age.toString() else ""

                    _cachedUserName.value = userName.value
                    _cachedUserAge.value = userAge.value
                }
        subscriptions.add(dispos)
    }
}
