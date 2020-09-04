package com.mobiledevpro.app.ui.mainscreen.viewmodel

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

class MainViewModel : BaseViewModel() {

    val editUserButton = SingleLiveData<Boolean>()


    fun onClickEditUser() {
        editUserButton.postValue(true)
    }
}