package com.mobiledevpro.interactor.usereditscreen

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

    fun updateUser(name: String): Single<Boolean>
}
