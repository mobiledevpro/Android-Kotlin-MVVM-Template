package com.mobiledevpro.domain.useredit

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
class UserEditInteractorImpl(private val userEditRepository: UserEditRepository) : UserEditInteractor {

    override fun updateUser(name: String?, age: Int): Single<Boolean> =
            userEditRepository.getUser()
                    .flatMap { user ->
                        user.name = name
                        user.age = age

                        return@flatMap userEditRepository.setUser(user)
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())

    override fun getUserObservable(): Observable<User> =
            userEditRepository.getUserObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())

}