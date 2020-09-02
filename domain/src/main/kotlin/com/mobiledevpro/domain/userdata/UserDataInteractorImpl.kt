package com.mobiledevpro.domain.userdata

import com.mobiledevpro.data.repository.userdata.UserDataRepositoryImpl
import com.mobiledevpro.domain.mapper.toData
import com.mobiledevpro.domain.mapper.toDomain
import com.mobiledevpro.domain.model.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

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

@Singleton
class UserDataInteractorImpl @Inject constructor(
    private val userDataRepository: UserDataRepositoryImpl
)  {

    fun updateUserData(name: String?, age: Int): Single<Boolean> =
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

    fun observeUserData(): Observable<User> =
        userDataRepository.getUserObservable()
            .map { it.toDomain() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}