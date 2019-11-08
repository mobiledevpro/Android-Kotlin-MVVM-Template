package com.mobiledevpro.apptemplate.uimodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiledevpro.data.LOG_TAG_DEBUG

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
class UserDataViewModel : ViewModel() {

    private val _userDataLive = MutableLiveData<String>()
    private val userDataLive: LiveData<String?>
        get() = _userDataLive

    init {
        Log.d(LOG_TAG_DEBUG, "UserDataViewModel created!")
        //init all the data here
        _userDataLive.value = "John"
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(LOG_TAG_DEBUG, "UserDataViewModel cleared!")
    }

    fun setUserName(name: String) {

        /*  val user = _userDataLive.value
          user?.name = name
          _userDataLive.value = user ?: User()*/

        _userDataLive.value = name

        Log.d(LOG_TAG_DEBUG, "UserDataViewModel name: ${name}")
    }

    fun getUserDataObserver(): LiveData<String?> = userDataLive
}
