package com.mobiledevpro.local.database

import com.mobiledevpro.local.database.model.UserEntity
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
interface DatabaseHelper {
    fun getUser(userId: Int): Single<UserEntity>

    fun getUserUpdatesObservable(): Observable<UserEntity>

    fun updateUser(userEntity: UserEntity): Single<Boolean>
}