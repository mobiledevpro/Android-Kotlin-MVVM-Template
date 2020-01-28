package com.mobiledevpro.app.ui.mainscreen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _editUserButton = MutableLiveData<Event<Boolean>>()
    val editUserButton: LiveData<Event<Boolean>> = _editUserButton


    fun onClickEditUser() {
        _editUserButton.value = Event(true)
    }
}