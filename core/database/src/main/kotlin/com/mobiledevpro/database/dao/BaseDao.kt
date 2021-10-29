package com.mobiledevpro.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Base interface
 *
 *
 */
internal interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(objList: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

}