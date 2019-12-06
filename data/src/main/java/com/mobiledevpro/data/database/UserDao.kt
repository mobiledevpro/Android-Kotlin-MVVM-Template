package com.mobiledevpro.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobiledevpro.data.model.User
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserLive(id: Int): LiveData<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserSingle(id: Int): Single<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserObservable(id: Int): Observable<User>
}