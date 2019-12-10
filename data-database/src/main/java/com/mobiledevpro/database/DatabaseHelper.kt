package com.mobiledevpro.database

import android.content.Context
import com.mobiledevpro.database.model.User
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Database Helper
 *
 * Created by Dmitriy Chernysh on 12/10/19.
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class DatabaseHelper(private val appContext: Context) : IDatabaseHelper {

    override fun getUser(userId: Int): Single<User> {
        return AppDatabase.getInstance(appContext)
                .userDao
                .getUserSingle(userId)
    }

    override fun getUserUpdatesObservable(): Observable<User> {
        return AppDatabase.getInstance(appContext)
                .userDao
                .getUserObservable(0)
    }

    override fun updateUser(user: User): Single<Boolean> {
        return Single.create { emitter ->

            AppDatabase.getInstance(appContext)
                    .userDao
                    .insert(user)

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
