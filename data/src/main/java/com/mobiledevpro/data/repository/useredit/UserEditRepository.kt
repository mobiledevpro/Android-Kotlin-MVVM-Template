package com.mobiledevpro.data.repository.useredit

import android.content.Context
import com.mobiledevpro.data.database.AppDatabase
import com.mobiledevpro.data.model.User
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Repository for UserEdit screen
 *
 *
 * Created by Dmitriy V. Chernysh on 11/7/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class UserEditRepository(private val appContext: Context) : IUserEditRepository {
    /*
        override fun getUserLiveData(): LiveData<User> {
            return AppDatabase.getInstance(appContext)
                    .userDao
                    .getUserLive(0)

        }
    */

    override fun getUser(): Single<User> {
        return AppDatabase.getInstance(appContext)
                .userDao
                .getUserSingle(0)
                .onErrorReturn {
                    User()
                }
    }

    override fun getUserObservable(): Observable<User> {
        return AppDatabase.getInstance(appContext)
                .userDao
                .getUserObservable(0)
                .onErrorReturn {
                    User()
                }
    }

    override fun setUser(user: User): Single<Boolean> {
        return Single.create { emitter ->

            AppDatabase.getInstance(appContext)
                    .userDao
                    .insert(user)

            emitter.onSuccess(true)
        }
    }
}

