package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.model.UserData
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
interface UserDataRepository {

    fun getUser(): Single<UserData>

    fun getUserObservable(): Observable<UserData>

    fun setUser(user: UserData): Single<Boolean>
}
