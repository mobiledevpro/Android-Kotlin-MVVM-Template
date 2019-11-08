package com.mobiledevpro.apptemplate.ui.mainscreen.presenter

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.mobiledevpro.data.LOG_TAG_DEBUG

/**
 * Presenter for main screen
 *
 * Created by Dmitriy V. Chernysh
 * dmitriy.chernysh@gmail.com
 *
 * https://instagr.am/mobiledevpro/
 *
 * #MobileDevPro
 */

class MainPresenter
constructor(private val view: IMain.View) : IMain.Presenter {

    override fun onStartView() {
        showMessage()
    }

    override fun onStopView() {

    }

    override fun onLifecycleEventTest(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d(LOG_TAG_DEBUG, "LIFE EVENT: ${event.name}")
    }

    private fun showMessage() {
/*
        Handler().postDelayed({
            view.showToast(R.string.app_name)
        }, 5000)
*/
    }
}
