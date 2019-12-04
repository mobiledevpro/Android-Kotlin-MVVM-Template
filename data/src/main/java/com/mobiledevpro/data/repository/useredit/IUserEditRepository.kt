package com.mobiledevpro.data.repository.useredit

import com.mobiledevpro.data.model.User
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Repository for UserEdit screen
 *
 *
 * Created by Dmitriy Chernysh on 11/7/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
interface IUserEditRepository {

    fun getUser(): Single<User>

    fun getUserObservable(): Observable<User>

    fun setUser(user: User): Single<Boolean>
}
