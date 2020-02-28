package com.mobiledevpro.app.ui.usereditscreen.presenter

import android.util.Log
import com.mobiledevpro.app.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.data.LOG_TAG_DEBUG
import com.mobiledevpro.data.repository.useredit.UserEditRepositoryImpl
import com.mobiledevpro.domain.model.User
import com.mobiledevpro.domain.useredit.UserEditInteractorImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * Presenter for edit user screen
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */

internal class UserEditPresenter(private val viewModel: UserDataViewModel) : IUserEditPresenter {

    private val interactor = UserEditInteractorImpl(UserEditRepositoryImpl(viewModel.getApplication()))
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
                .addTo(subscriptions)
    }

    private fun observeUserData() {
        interactor.getUserObservable()
                .subscribeBy { user ->
                    viewModel.setUserData(user)
                }
                .addTo(subscriptions)
    }
}
