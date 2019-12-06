package com.mobiledevpro.apptemplate

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobiledevpro.apptemplate.ui.mainscreen.viewmodel.MainViewModel
import com.mobiledevpro.apptemplate.ui.mainscreen.viewmodel.UserDataViewModel

/**
 * Factory for View Models
 *
 * Created by Dmitriy Chernysh on 11/12/19.
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class ViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(UserDataViewModel::class.java) ->
                        UserDataViewModel(app)
                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(app)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")

                }
            } as T
}