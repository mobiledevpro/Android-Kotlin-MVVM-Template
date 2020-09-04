package com.mobiledevpro.local.database

import android.content.Context
import com.mobiledevpro.local.database.model.UserEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Database Helper
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */
@Singleton
class DatabaseHelperImpl @Inject constructor(
    @ApplicationContext private val appContext: Context
)  {

    fun getUser(userId: Int): Single<UserEntity> {
        return AppDatabase.getInstance(appContext)
            .userDao
            .getUserSingle(userId)
    }

    fun getUserUpdatesObservable(): Observable<UserEntity> {
        return AppDatabase.getInstance(appContext)
            .userDao
            .getUserObservable(0)
    }

    fun updateUser(userEntity: UserEntity): Single<Boolean> {
        return Single.create { emitter ->

            AppDatabase.getInstance(appContext)
                .userDao
                .insert(userEntity)

            emitter.onSuccess(true)
        }
    }
}
