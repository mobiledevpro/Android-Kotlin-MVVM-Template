package com.mobiledevpro.app.di

import com.mobiledevpro.data.repository.userdata.UserDataRepository
import com.mobiledevpro.data.repository.userdata.UserDataRepositoryImpl
import org.koin.dsl.module


/**
 * App Koin module
 *
 */
fun getModules() = listOf(
    domainModule,
    dataModule,
    dataLocalModule,
    dataRemoteModule
)

val domainModule = module {
   // single<UserDataInteractor> { UserDataInteractorImpl(get()) }
}

val dataModule = module {
   // single<UserDataRepository> { UserDataRepositoryImpl(get()) }
}

val dataLocalModule = module {
   // single<DatabaseHelper> { DatabaseHelperImpl(get()) }
   // single<StorageHelper> { StorageHelperImpl(get()) }
  //  single<PreferencesHelper> { PreferencesHelperImpl(get()) }
}

val dataRemoteModule = module {
    // TODO: 2/29/20 retrofit instance, firebase database, etc
}

