package com.mobiledevpro.domain.useredit

import com.mobiledevpro.domain.model.User
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Repository for UserEdit screen
 *
 * NOTE: See implementation in the data module
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
interface UserEditRepository {

    fun getUser(): Single<User>

    fun getUserObservable(): Observable<User>

    fun setUser(user: User): Single<Boolean>
}
