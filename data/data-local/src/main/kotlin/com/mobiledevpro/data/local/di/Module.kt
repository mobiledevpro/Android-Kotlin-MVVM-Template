package com.mobiledevpro.data.local.di

import com.mobiledevpro.data.local.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Koin module
 */

val dataLocalModule = module {

    single {
        AppDatabase.buildDatabase(androidContext())
    }
}
