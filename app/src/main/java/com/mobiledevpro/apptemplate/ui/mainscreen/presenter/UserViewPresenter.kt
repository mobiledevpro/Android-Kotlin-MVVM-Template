package com.mobiledevpro.apptemplate.ui.mainscreen.presenter

import android.util.Log
import com.mobiledevpro.data.LOG_TAG_DEBUG

/**
 * Presenter for UserView screen
 *
 * Created by Dmitriy Chernysh on 11/7/19.
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class UserViewPresenter
constructor(view: IUserView.View) : IUserView.Presenter {

    override fun onStartView() {
        Log.d(LOG_TAG_DEBUG, "UserViewPresenter START_VIEW")
    }

    override fun onStopView() {
        Log.d(LOG_TAG_DEBUG, "UserViewPresenter STOP_VIEW")
    }
}