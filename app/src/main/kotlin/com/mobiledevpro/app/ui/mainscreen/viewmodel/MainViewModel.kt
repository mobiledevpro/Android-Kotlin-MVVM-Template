package com.mobiledevpro.app.ui.mainscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobiledevpro.app.Event
import com.mobiledevpro.app.ui.BaseViewModel

/**
 * ViewModel for main fragment
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class MainViewModel : BaseViewModel() {

    private val _editUserButton = MutableLiveData<Event<Boolean>>()
    val editUserButton: LiveData<Event<Boolean>> = _editUserButton


    fun onClickEditUser() {
        _editUserButton.value = Event(true)
    }
}