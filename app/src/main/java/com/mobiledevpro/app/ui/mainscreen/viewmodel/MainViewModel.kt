package com.mobiledevpro.app.ui.mainscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiledevpro.app.Event

/**
 * ViewModel for main fragment
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class MainViewModel : ViewModel() {

    private val _editUserButton = MutableLiveData<Event<Boolean>>()
    val editUserButton: LiveData<Event<Boolean>> = _editUserButton


    fun onClickEditUser() {
        _editUserButton.value = Event(true)
    }
}