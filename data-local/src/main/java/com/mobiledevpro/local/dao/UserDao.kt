package com.mobiledevpro.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobiledevpro.local.model.UserEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
internal interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserSingle(id: Int): Single<UserEntity>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserObservable(id: Int): Observable<UserEntity>
}