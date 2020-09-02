package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.mapper.toEntity
import com.mobiledevpro.data.mapper.toUser
import com.mobiledevpro.data.model.UserData
import com.mobiledevpro.local.database.DatabaseHelperImpl
import com.mobiledevpro.local.database.model.UserEntity
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

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
@Singleton
class UserDataRepositoryImpl @Inject constructor(
    private val databaseHelper: DatabaseHelperImpl
)  {

    fun getUser(): Single<UserData> =
        databaseHelper.getUser(0)
            .onErrorReturn { UserEntity() }
            .map(UserEntity::toUser)

    fun getUserObservable(): Observable<UserData> =
        databaseHelper.getUserUpdatesObservable()
            .onErrorReturn { UserEntity() }
            .map(UserEntity::toUser)

    fun setUser(user: UserData): Single<Boolean> =
        Single.just(user)
            .map(UserData::toEntity)
            .flatMap(databaseHelper::updateUser)

}

