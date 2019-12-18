package com.mobiledevpro.apptemplate.ui.usereditscreen.presenter

import android.util.Log
import com.mobiledevpro.apptemplate.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.data.LOG_TAG_DEBUG
import com.mobiledevpro.database.model.User
import com.mobiledevpro.interactor.useredit.UserEditInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

/**
 * Class for ...
 *
 * Created by Dmitriy Chernysh on 12/17/19.
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */

internal class UserEditPresenter(private val viewModel: UserDataViewModel) : IUserEditPresenter {

    private val interactor = UserEditInteractor(viewModel.getApplication())
    private var subscriptions = CompositeDisposable()

    override fun onStart() {
        Log.d(LOG_TAG_DEBUG, "UserEditPresenter.onStart()")
        subscriptions = CompositeDisposable()

        //observe user data changes
        observeUserData()
    }

    override fun onStop() {
        Log.d(LOG_TAG_DEBUG, "UserEditPresenter.onStop()")
        subscriptions.dispose()
    }

    override fun saveUserData(user: User) {
        subscriptions.add(
                interactor.updateUser(user.name, user.age)
                        .subscribeBy(
                                {
                                    Log.e(LOG_TAG_DEBUG, it.localizedMessage ?: "Something wrong")
                                    viewModel.showToastMessage("Error: " + it.localizedMessage)

                                },
                                {
                                    Log.d(LOG_TAG_DEBUG, "Saved!")
                                    viewModel.showToastMessage("Saved!")
                                    viewModel.navigateToUserViewScreen()
                                })
        )
    }

    private fun observeUserData() {
        subscriptions.add(
                interactor.getUserObservable()
                        .subscribeBy { user ->
                            viewModel.setUserData(user)
                        })
    }
}
