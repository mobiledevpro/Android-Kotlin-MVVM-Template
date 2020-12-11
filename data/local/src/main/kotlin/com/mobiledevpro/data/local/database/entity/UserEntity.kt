package com.mobiledevpro.data.local.database.entity

import androidx.room.Entity
import androidx.room.Index

/**
 * User
 *
 * Created on Dec 11, 2020.
 *
 */
@Entity(
    tableName = "user",
    indices = [
        Index(value = ["id"])
    ],
    primaryKeys = ["id"]
)
data class UserEntity(
    val id: String,
    val name: String,
    val email: String
)
