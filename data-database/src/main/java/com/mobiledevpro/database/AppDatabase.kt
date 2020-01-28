package com.mobiledevpro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiledevpro.database.dao.UserDao
import com.mobiledevpro.database.model.User

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
            User::class
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
                            context.applicationContext,
                            AppDatabase::class.java,
                            "app_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

