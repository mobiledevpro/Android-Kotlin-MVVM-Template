package com.mobiledevpro.interactor.useredit

import com.mobiledevpro.database.model.User
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Interactor for UserEdit screen
 *
 *
 * Created by Dmitriy Chernysh on 11/7/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
interface IUserEditInteractor {

    fun getUserObservable(): Observable<User>

    fun updateUser(name: String,
                   age: Int): Single<Boolean>
}
