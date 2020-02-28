package com.mobiledevpro.domain.useredit

import com.mobiledevpro.local.model.User
import io.reactivex.Observable
import io.reactivex.Single

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
interface IUserEditInteractor {

    fun getUserObservable(): Observable<User>

    fun updateUser(name: String,
                   age: Int): Single<Boolean>
}
