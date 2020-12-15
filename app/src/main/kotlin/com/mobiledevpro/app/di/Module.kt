package com.mobiledevpro.app.di

import com.mobiledevpro.app.helper.ImplResourcesProvider
import com.mobiledevpro.app.helper.ResourcesProvider
import com.mobiledevpro.data.local.di.dataLocalModule
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


/**
 * App Koin module
 *
 */
fun getModules() = listOf(
    presentationCommonModule,
    dataLocalModule,
    dataRemoteModule
)

val presentationCommonModule = module {
    single<ResourcesProvider> {
        ImplResourcesProvider(androidApplication().resources)
    }
}

val dataRemoteModule = module {
    // TODO: 2/29/20 retrofit instance, firebase database, etc
}

