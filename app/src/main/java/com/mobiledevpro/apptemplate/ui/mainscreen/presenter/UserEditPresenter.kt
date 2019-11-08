package com.mobiledevpro.apptemplate.ui.mainscreen.presenter

import android.util.Log
import com.mobiledevpro.apptemplate.App
import com.mobiledevpro.data.LOG_TAG_DEBUG
import com.mobiledevpro.interactor.usereditscreen.IUserEditInteractor
import com.mobiledevpro.interactor.usereditscreen.UserEditInteractor

/**
 * Presenter for UserEdit screen
 *
 *
 * Created by Dmitriy V. Chernysh on 11/7/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class UserEditPresenter
constructor(view: IUserEdit.View) : IUserEdit.Presenter {

    private var view: IUserEdit.View = view

    private var interactor: IUserEditInteractor = UserEditInteractor(App.appContext)

    override fun onStartView() {
        Log.d(LOG_TAG_DEBUG, "UserEditPresenter START_VIEW")
    }

    override fun onStopView() {
        Log.d(LOG_TAG_DEBUG, "UserEditPresenter STOP_VIEW")
    }

    override fun updateUserName(name: String?) {
        if (name.isNullOrEmpty()) {
            view.showShortMessage("Enter user name")
            return
        }

        /* interactor.updateUser(name)
                 .subscribeBy(  // named arguments for lambda Subscribers
                         onError = { Log.e(LOG_TAG_ERROR, "ERROR: " + it.localizedMessage); },
                         onSuccess = { Log.d(LOG_TAG_DEBUG, "SUCCESS"); }
                 )*/

        view.showShortMessage("User name: $name")
    }
}