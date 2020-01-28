package com.mobiledevpro.data.repository.useredit

import android.content.Context
import com.mobiledevpro.database.DatabaseHelper
import com.mobiledevpro.database.model.User
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Repository for UserEdit screen
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class UserEditRepository(appContext: Context) : IUserEditRepository {

    private var databaseHelper = DatabaseHelper.getInstance(appContext)

    override fun getUser(): Single<User> {
        return databaseHelper.getUser(0)
                .onErrorReturn {
                    User()
                }
    }

    override fun getUserObservable(): Observable<User> {
        return databaseHelper.getUserUpdatesObservable()
                .onErrorReturn {
                    User()
                }
    }

    override fun setUser(user: User): Single<Boolean> {
        return databaseHelper.updateUser(user)
    }
}

