package com.mobiledevpro.app.di

import com.mobiledevpro.app.helper.ImplResourcesProvider
import com.mobiledevpro.app.helper.ResourcesProvider
import com.mobiledevpro.database.di.coreDatabaseModule
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


/**
 * App Koin module
 *
 */
fun getModules() = listOf(
    presentationCommonModule,
    coreDatabaseModule
)

val presentationCommonModule = module {
    single<ResourcesProvider> {
        ImplResourcesProvider(androidApplication().resources)
    }
}

