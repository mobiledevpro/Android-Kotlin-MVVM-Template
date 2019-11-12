package com.mobiledevpro.apptemplate.uimodel

import android.util.Log
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

    val userName = MutableLiveData<String>()
    val userAge = MutableLiveData<Int>()

    init {
        Log.d(LOG_TAG_DEBUG, "UserDataViewModel created!")
        //init all the data here
        userName.value = "John"
        userAge.value = 30
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(LOG_TAG_DEBUG, "UserDataViewModel cleared!")
    }


    fun updateUser() {
        var name = userName.value
        var age = userAge.value

        //TODO: call interactor and then repo
    }
}
