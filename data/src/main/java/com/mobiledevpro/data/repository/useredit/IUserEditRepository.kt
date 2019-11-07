package com.mobiledevpro.data.repository.useredit

import com.mobiledevpro.data.model.UserData
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

    fun getUser(): Single<UserData>

    fun setUser(user: UserData): Single<Boolean>
}
