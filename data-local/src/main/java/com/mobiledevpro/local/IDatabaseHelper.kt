package com.mobiledevpro.local

import com.mobiledevpro.local.model.UserEntity
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Database Helper
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
interface IDatabaseHelper {
    fun getUser(userId: Int): Single<UserEntity>

    fun getUserUpdatesObservable(): Observable<UserEntity>

    fun updateUser(userEntity: UserEntity): Single<Boolean>
}