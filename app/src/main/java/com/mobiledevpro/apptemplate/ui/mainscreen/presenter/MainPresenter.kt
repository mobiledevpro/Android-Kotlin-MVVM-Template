package com.mobiledevpro.apptemplate.ui.mainscreen.presenter

import com.mobiledevpro.apptemplate.R

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

    private fun showMessage() {
        view.showToast(R.string.app_name)
    }
}
