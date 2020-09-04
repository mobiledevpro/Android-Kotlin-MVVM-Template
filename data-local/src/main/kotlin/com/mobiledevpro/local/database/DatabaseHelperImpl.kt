package com.mobiledevpro.local.database

import android.content.Context
import com.mobiledevpro.local.database.model.UserEntity
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Database Helper
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
class DatabaseHelperImpl(
    private val appContext: Context
) : DatabaseHelper {

    override fun getUser(userId: Int): Single<UserEntity> {
        return AppDatabase.getInstance(appContext)
            .userDao
            .getUserSingle(userId)
    }

    override fun getUserUpdatesObservable(): Observable<UserEntity> {
        return AppDatabase.getInstance(appContext)
            .userDao
            .getUserObservable(0)
    }

    override fun updateUser(userEntity: UserEntity): Single<Boolean> {
        return Single.create { emitter ->

            AppDatabase.getInstance(appContext)
                .userDao
                .insert(userEntity)

            emitter.onSuccess(true)
        }
    }
}
