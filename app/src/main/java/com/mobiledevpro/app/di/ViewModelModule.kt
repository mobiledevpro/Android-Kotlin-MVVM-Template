package com.mobiledevpro.app.di

import com.mobiledevpro.app.ui.mainscreen.viewmodel.MainViewModel
import com.mobiledevpro.app.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.data.repository.userdata.UserDataRepositoryImpl
import com.mobiledevpro.domain.userdata.UserDataInteractor
import com.mobiledevpro.domain.userdata.UserDataInteractorImpl
import com.mobiledevpro.domain.userdata.UserDataRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for ViewModels
 *
 * Created by Dmitriy Chernysh on Feb 29, 2020.
 *
 * http://androiddev.pro
 *
 */
val viewModelModule = module {
    single<UserDataRepository> { UserDataRepositoryImpl(get()) }
    single<UserDataInteractor> { UserDataInteractorImpl(get()) }

    viewModel { MainViewModel() }
    viewModel { UserDataViewModel(get()) }
}