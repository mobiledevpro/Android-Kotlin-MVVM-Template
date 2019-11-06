package com.mobiledevpro.apptemplate.ui.mainscreen.presenter

import com.mobiledevpro.apptemplate.App
import com.mobiledevpro.data.storage.PreferencesHelper

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

class MainPresenter : IMain.Presenter {

    private var mView: IMain.View? = null

    override fun bindView(view: IMain.View) {
        mView = view

        PreferencesHelper.getInstance(App.appContext)
                .something = "Test"
    }

    override fun unbindView() {
        mView = null
    }
}
