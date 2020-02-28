package com.mobiledevpro.local

import android.content.Context
import com.mobiledevpro.local.model.UserEntity
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
class DatabaseHelper(private val appContext: Context) : IDatabaseHelper {

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

    companion object {
        @Volatile
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = DatabaseHelper(context)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
