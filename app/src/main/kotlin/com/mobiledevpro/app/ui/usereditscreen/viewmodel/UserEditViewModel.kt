package com.mobiledevpro.app.ui.usereditscreen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mobiledevpro.common.ui.base.BaseViewModel
import com.mobiledevpro.common.ui.livedata.Event
import com.mobiledevpro.data.LOG_TAG_DEBUG
import com.mobiledevpro.domain.model.User
import com.mobiledevpro.domain.userdata.UserDataInteractor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * ViewModel for edit user screen
 *
 * Created by Dmitri Chernysh on Sep 02, 2020.
 *
 * http://mobile-dev.pro
 *
 */

class UserEditViewModel constructor(
    private val interactor: UserDataInteractor
) : BaseViewModel() {

    private val _cachedUserData = MutableLiveData<User>()

    private val _navigateToUserView = MutableLiveData<Event<Boolean>>()
    val navigateToUserView: LiveData<Event<Boolean>> = _navigateToUserView

    private val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>> = _showToastEvent

    //these two fields uses two-way databinding
    val userName = MutableLiveData<String>()
    val userAge = MutableLiveData<String>()

    init {
        observeUserData()
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

    private fun isUserDataChanged(): Boolean {
        val cachedUser = _cachedUserData.value

        return (userName.value != cachedUser?.name) ||
            (userAge.value != cachedUser?.age.toString())
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
            .addTo(subscriptions)
    }

    private fun showToastMessage(message: String) {
        _showToastEvent.value = Event(message)
    }

    private fun navigateToUserViewScreen() {
        _navigateToUserView.value = Event(true)
    }

    private fun observeUserData() {
        interactor.observeUserData()
            .subscribeBy { user ->
                _cachedUserData.value = user
                userName.value = user.name
                userAge.value = user.age.toString()
            }
            .addTo(subscriptions)
    }
}