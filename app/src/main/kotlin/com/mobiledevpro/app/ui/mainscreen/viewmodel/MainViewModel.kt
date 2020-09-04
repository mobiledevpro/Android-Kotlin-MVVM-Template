package com.mobiledevpro.app.ui.mainscreen.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.mobiledevpro.common.ui.base.BaseViewModel
import com.mobiledevpro.common.ui.livedata.SingleLiveData

/**
 * ViewModel for main fragment
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */

class MainViewModel @ViewModelInject constructor() : BaseViewModel() {

    val editUserButton = SingleLiveData<Boolean>()


    fun onClickEditUser() {
        editUserButton.postValue(true)
    }
}