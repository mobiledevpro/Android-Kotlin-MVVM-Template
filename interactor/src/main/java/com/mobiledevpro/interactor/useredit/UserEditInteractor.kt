package com.mobiledevpro.interactor.useredit

import android.content.Context
import com.mobiledevpro.data.model.User
import com.mobiledevpro.data.repository.useredit.IUserEditRepository
import com.mobiledevpro.data.repository.useredit.UserEditRepository
import io.reactivex.Observable
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

    override fun updateUser(name: String, age: Int): Single<Boolean> {
        return repository.getUserObservable()
                .firstOrError()
                .flatMap { user ->
                    user.name = name
                    user.age = age

                    return@flatMap repository.setUser(user)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    // override fun getUserData(): LiveData<User> =
    //        repository.getUserLiveData()

    override fun getUserObservable(): Observable<User> {
        return repository.getUserObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}