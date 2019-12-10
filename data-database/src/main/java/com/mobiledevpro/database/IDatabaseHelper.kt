package com.mobiledevpro.database

import com.mobiledevpro.database.model.User
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Database Helper
 *
 * Created by Dmitriy Chernysh on 12/10/19.
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
public interface IDatabaseHelper {
    fun getUser(userId: Int): Single<User>

    fun getUserUpdatesObservable(): Observable<User>

    fun updateUser(user: User): Single<Boolean>
}