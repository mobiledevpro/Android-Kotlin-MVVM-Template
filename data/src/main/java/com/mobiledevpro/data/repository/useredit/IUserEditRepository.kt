package com.mobiledevpro.data.repository.useredit

import com.mobiledevpro.database.model.User
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Repository for UserEdit screen
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
interface IUserEditRepository {

    fun getUser(): Single<User>

    fun getUserObservable(): Observable<User>

    fun setUser(user: User): Single<Boolean>
}
