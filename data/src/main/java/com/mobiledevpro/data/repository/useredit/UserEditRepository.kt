package com.mobiledevpro.data.repository.useredit

import android.content.Context
import android.util.Log
import com.mobiledevpro.data.LOG_TAG_DEBUG
import com.mobiledevpro.data.model.User
import com.mobiledevpro.data.storage.PreferencesHelper
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


    override fun setUser(user: User): Single<Boolean> {
        return Single.create { emitter ->
            Log.d(LOG_TAG_DEBUG, "User name: " + user.name)

            PreferencesHelper.getInstance(appContext)
                    .something = user.name

            emitter.onSuccess(true)
        }
    }

    override fun getUser(): Single<User> {
        return Single.create { emitter ->
            val userName: String? = PreferencesHelper.getInstance(appContext).something
            val user: User = User()
            user.name = userName ?: ""
            emitter.onSuccess(user)
        }
    }
}

