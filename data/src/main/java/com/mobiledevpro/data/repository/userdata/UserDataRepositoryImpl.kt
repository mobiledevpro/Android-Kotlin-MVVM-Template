package com.mobiledevpro.data.repository.userdata

import com.mobiledevpro.data.toEntity
import com.mobiledevpro.data.toUser
import com.mobiledevpro.domain.model.User
import com.mobiledevpro.domain.userdata.UserDataRepository
import com.mobiledevpro.local.database.DatabaseHelper
import com.mobiledevpro.local.database.model.UserEntity
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
class UserDataRepositoryImpl(private val databaseHelper: DatabaseHelper) : UserDataRepository {

    override fun getUser(): Single<User> =
            databaseHelper.getUser(0)
                    .onErrorReturn { UserEntity() }
                    .map(UserEntity::toUser)

    override fun getUserObservable(): Observable<User> =
            databaseHelper.getUserUpdatesObservable()
                    .onErrorReturn { UserEntity() }
                    .map(UserEntity::toUser)

    override fun setUser(user: User): Single<Boolean> =
            Single.just(user)
                    .map(User::toEntity)
                    .flatMap(databaseHelper::updateUser)

}

