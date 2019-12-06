package com.mobiledevpro.apptemplate.ui.mainscreen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobiledevpro.apptemplate.Event

/**
 * ViewModel for main fragment
 *
 * Created by Dmitriy Chernysh on 12/6/19.
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _onClickEditUser = MutableLiveData<Event<Boolean>>()
    val onClickEditUser: LiveData<Event<Boolean>> = _onClickEditUser


    fun onClickEditUser() {
        _onClickEditUser.value = Event(true)
    }
}