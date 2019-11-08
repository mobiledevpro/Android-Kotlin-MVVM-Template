package com.mobiledevpro.interactor.usereditscreen

import android.content.Context
import com.mobiledevpro.data.model.User
import com.mobiledevpro.data.repository.useredit.IUserEditRepository
import com.mobiledevpro.data.repository.useredit.UserEditRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
class UserEditInteractor(appContext: Context) : IUserEditInteractor {

    private val repository: IUserEditRepository

    init {
        repository = UserEditRepository(appContext)
    }


    override fun updateUser(name: String): Single<Boolean> {
        return repository.getUser()
                .flatMap { user: User ->
                    //   user.name = name
                    repository.setUser(user)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }
}