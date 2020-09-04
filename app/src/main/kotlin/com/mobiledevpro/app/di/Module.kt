package com.mobiledevpro.app.di

import com.mobiledevpro.app.ui.mainscreen.viewmodel.MainViewModel
import com.mobiledevpro.app.ui.usereditscreen.viewmodel.UserEditViewModel
import com.mobiledevpro.app.ui.userviewscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.data.repository.userdata.UserDataRepository
import com.mobiledevpro.data.repository.userdata.UserDataRepositoryImpl
import com.mobiledevpro.domain.userdata.UserDataInteractor
import com.mobiledevpro.domain.userdata.UserDataInteractorImpl
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


fun getModules() = listOf(
    uiModule,
    domainModule,
    dataModule,
    dataLocalModule,
    dataRemoteModule
)

val uiModule = module {
    viewModel { MainViewModel() }
    viewModel { UserDataViewModel(get()) }
    viewModel { UserEditViewModel(get()) }
}

val domainModule = module {
    single<UserDataInteractor> { UserDataInteractorImpl(get()) }
}

val dataModule = module {
    single<UserDataRepository> { UserDataRepositoryImpl(get()) }
}

val dataLocalModule = module {
    single<DatabaseHelper> { DatabaseHelperImpl(get()) }
   // single<StorageHelper> { StorageHelperImpl(get()) }
  //  single<PreferencesHelper> { PreferencesHelperImpl(get()) }
}

val dataRemoteModule = module {
    // TODO: 2/29/20 retrofit instance, firebase database, etc
}

