package com.mobiledevpro.local

import com.mobiledevpro.local.model.User
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
    fun getUser(userId: Int): Single<User>

    fun getUserUpdatesObservable(): Observable<User>

    fun updateUser(user: User): Single<Boolean>
}