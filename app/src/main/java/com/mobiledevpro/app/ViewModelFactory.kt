package com.mobiledevpro.app

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobiledevpro.app.ui.mainscreen.viewmodel.MainViewModel
import com.mobiledevpro.app.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.data.repository.userdata.UserDataRepositoryImpl
import com.mobiledevpro.domain.userdata.UserDataInteractorImpl

/**
 * Factory for View Models
 *
 * Created by Dmitriy Chernysh on 11/12/19.
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class ViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(UserDataViewModel::class.java) ->
                        UserDataViewModel(
                                UserDataInteractorImpl(UserDataRepositoryImpl(app))
                        )
                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(app)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")

                }
            } as T
}