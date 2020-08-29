package com.mobiledevpro.domain.userdata

import com.mobiledevpro.domain.model.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Interactor for UserEdit screen
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class UserDataInteractorImpl(private val userDataRepository: UserDataRepository) : UserDataInteractor {

    override fun updateUserData(name: String?, age: Int): Single<Boolean> =
            userDataRepository.getUser()
                    .flatMap { user ->
                        user.name = name
                        user.age = age

                        return@flatMap userDataRepository.setUser(user)
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())

    override fun observeUserData(): Observable<User> =
            userDataRepository.getUserObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())

}