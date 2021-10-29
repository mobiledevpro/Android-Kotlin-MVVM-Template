package com.mobiledevpro.database.entity

import androidx.room.Entity
import androidx.room.Index

/**
 * User
 *
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
