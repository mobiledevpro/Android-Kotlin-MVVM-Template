package com.mobiledevpro.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiledevpro.local.BuildConfig
import com.mobiledevpro.local.database.dao.UserDao
import com.mobiledevpro.local.database.model.UserEntity

/**
 * Room Database
 *
 * Created by Dmitriy Chernysh on 11/12/19.
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */

@Database(
    entities = [
        UserEntity::class
    ],
    version = BuildConfig.RoomDatabaseVersion,
    exportSchema = true
)

internal abstract class AppDatabase : RoomDatabase() {
    internal abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        BuildConfig.RoomDatabaseName)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

