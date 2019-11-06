package com.mobiledevpro.apptemplate

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.squareup.leakcanary.LeakCanary
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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }

        sApp = this

        LeakCanary.install(this)

        val crashlyticsKit = Crashlytics.Builder()
                //.core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .core(CrashlyticsCore.Builder().build())
                .build()
        Fabric.with(this, crashlyticsKit)
    }

    companion object {
        private lateinit var sApp: App

        val appContext: Context
            get() = sApp.applicationContext
    }
}
