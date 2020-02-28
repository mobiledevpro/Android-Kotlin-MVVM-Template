package com.mobiledevpro.app.ui.mainscreen.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.mobiledevpro.app.Event
import com.mobiledevpro.data.LOG_TAG_DEBUG
import com.mobiledevpro.domain.model.User
import com.mobiledevpro.domain.userdata.UserDataInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy


/**
 * ViewModel for user data
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class UserDataViewModel(private val interactor: UserDataInteractor) : ViewModel(), LifecycleObserver {

    private var subscriptions = CompositeDisposable()

    private val _cachedUserData = MutableLiveData<User>()

    private val _navigateToUserView = MutableLiveData<Event<Boolean>>()
    private val _showToastEvent = MutableLiveData<Event<String>>()

    //these two fields uses two-way databinding
    val userName = MutableLiveData<String>()
    val userAge = MutableLiveData<String>()

    val navigateToUserView: LiveData<Event<Boolean>> = _navigateToUserView
    val showToastEvent: LiveData<Event<String>> = _showToastEvent

    init {
        Log.d(LOG_TAG_DEBUG, "UserDataViewModel created!")
        //do nothing
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartView() {
        observeUserData()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopView() {
        subscriptions.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(LOG_TAG_DEBUG, "UserDataViewModel cleared!")
        //do nothing
    }

    /**
     * It calls from xml layout
     */
    fun onClickUpdateUser() {
        val user = _cachedUserData.value ?: User()
        user.name = userName.value ?: ""
        user.age = if (userAge.value.isNullOrEmpty()) 0 else userAge.value?.toInt() ?: 0

        updateUserData(user)
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

    fun showToastMessage(message: String) {
        _showToastEvent.value = Event(message)
    }

    fun navigateToUserViewScreen() {
        _navigateToUserView.value = Event(true)
    }

    private fun isUserDataChanged(): Boolean {
        val cachedUser = _cachedUserData.value

        return (userName.value != cachedUser?.name) ||
                (userAge.value != cachedUser?.age.toString())
    }


    private fun observeUserData() {
        interactor.observeUserData()
                .subscribeBy { user ->
                    _cachedUserData.value = user
                    userName.value = user.name
                    userAge.value = user.age.toString()
                }
                .addTo(subscriber())
    }

    private fun updateUserData(user: User) {
        interactor.updateUserData(user.name, user.age)
                .subscribeBy(
                        {
                            Log.e(LOG_TAG_DEBUG, it.localizedMessage ?: "Something wrong")
                            showToastMessage("Error: " + it.localizedMessage)

                        },
                        {
                            Log.d(LOG_TAG_DEBUG, "Saved!")
                            showToastMessage("Saved!")
                            navigateToUserViewScreen()
                        })
                .addTo(subscriber())
    }

    private fun subscriber(): CompositeDisposable =
            if (!subscriptions.isDisposed) subscriptions else CompositeDisposable()


}
