package com.mobiledevpro.app.di

import com.mobiledevpro.data.local.di.dataLocalModule
import org.koin.dsl.module


/**
 * App Koin module
 *
 */
fun getModules() = listOf(
    dataLocalModule,
    dataRemoteModule
)

val dataRemoteModule = module {
    // TODO: 2/29/20 retrofit instance, firebase database, etc
}

