package com.mobiledevpro.domain.userdata

import com.mobiledevpro.data.repository.userdata.UserDataRepository
import com.mobiledevpro.data.repository.userdata.UserDataRepositoryImpl
import com.mobiledevpro.domain.mapper.toData
import com.mobiledevpro.domain.mapper.toDomain
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

class UserDataInteractorImpl constructor(
    private val userDataRepository: UserDataRepository
) : UserDataInteractor {

    override fun updateUserData(name: String?, age: Int): Single<Boolean> =
        userDataRepository.getUser()
            .map { it.toDomain() }
            .map { user ->
                user.name = name
                user.age = age
                user
            }
            .map { it.toData() }
            .flatMap {
                userDataRepository.setUser(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    override fun observeUserData(): Observable<User> =
        userDataRepository.getUserObservable()
            .map { it.toDomain() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}