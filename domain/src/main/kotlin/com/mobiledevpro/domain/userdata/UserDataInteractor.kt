package com.mobiledevpro.domain.userdata

import com.mobiledevpro.domain.model.User
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

interface UserDataInteractor {

    fun observeUserData(): Observable<User>

    fun updateUserData(name: String?,
                       age: Int): Single<Boolean>
}
