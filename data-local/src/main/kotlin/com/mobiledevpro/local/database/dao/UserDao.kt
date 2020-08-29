package com.mobiledevpro.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.mobiledevpro.local.database.model.UserEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
internal interface UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserSingle(id: Int): Single<UserEntity>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserObservable(id: Int): Observable<UserEntity>
}