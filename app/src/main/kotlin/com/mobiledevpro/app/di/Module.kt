package com.mobiledevpro.app.di

import com.mobiledevpro.app.ui.mainscreen.viewmodel.MainViewModel
import com.mobiledevpro.app.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.data.repository.userdata.UserDataRepositoryImpl
import com.mobiledevpro.domain.userdata.UserDataInteractor
import com.mobiledevpro.domain.userdata.UserDataInteractorImpl
import com.mobiledevpro.domain.userdata.UserDataRepository
import com.mobiledevpro.local.database.DatabaseHelper
import com.mobiledevpro.local.database.DatabaseHelperImpl
import com.mobiledevpro.local.storage.PreferencesHelper
import com.mobiledevpro.local.storage.PreferencesHelperImpl
import com.mobiledevpro.local.storage.StorageHelper
import com.mobiledevpro.local.storage.StorageHelperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin modules
 *
 * Created by Dmitriy Chernysh on Feb 29, 2020.
 *
 * http://androiddev.pro
 *
 */

val uiModule = module {
    viewModel { MainViewModel() }
    viewModel { UserDataViewModel(get()) }
}

val domainModule = module {
    single { UserDataInteractorImpl(get()) as UserDataInteractor }
}

val dataModule = module {
    single { UserDataRepositoryImpl(get()) as UserDataRepository }
}

val dataLocalModule = module {
    single { DatabaseHelperImpl(get()) as DatabaseHelper }
    single { StorageHelperImpl(get()) as StorageHelper }
    single { PreferencesHelperImpl(get()) as PreferencesHelper }
}

val dataRemoteModule = module {
    // TODO: 2/29/20 retrofit instance, firebase database, etc
}

