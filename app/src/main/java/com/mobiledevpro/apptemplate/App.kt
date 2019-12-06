package com.mobiledevpro.apptemplate

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric

/**
 * Main application class
 *
 * Created by Dmitriy V. Chernysh
 * dmitriy.chernysh@gmail.com
 *
 * https://instagr.am/mobiledevpro/
 *
 * #MobileDevPro
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val crashlyticsKit = Crashlytics.Builder()
                //.core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .core(CrashlyticsCore.Builder().build())
                .build()
        Fabric.with(this, crashlyticsKit)
    }
}
