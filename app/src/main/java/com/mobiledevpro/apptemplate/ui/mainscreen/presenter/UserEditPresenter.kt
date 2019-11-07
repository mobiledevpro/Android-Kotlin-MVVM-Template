package com.mobiledevpro.apptemplate.ui.mainscreen.presenter

import android.util.Log
import com.mobiledevpro.apptemplate.App
import com.mobiledevpro.data.LOG_TAG_DEBUG
import com.mobiledevpro.data.LOG_TAG_ERROR
import com.mobiledevpro.interactor.usereditscreen.IUserEditInteractor
import com.mobiledevpro.interactor.usereditscreen.UserEditInteractor
import io.reactivex.rxkotlin.subscribeBy

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

    private var interactor: IUserEditInteractor = UserEditInteractor(App.appContext)

    override fun onStartView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStopView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserName(name: String) {
        interactor.updateUser(name)
                .subscribeBy(  // named arguments for lambda Subscribers
                        onError = { Log.e(LOG_TAG_ERROR, "ERROR: " + it.localizedMessage); },
                        onSuccess = { Log.d(LOG_TAG_DEBUG, "SUCCESS"); }
                )
    }
}