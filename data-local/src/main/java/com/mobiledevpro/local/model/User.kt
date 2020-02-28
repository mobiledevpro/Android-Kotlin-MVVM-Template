package com.mobiledevpro.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User {

    @PrimaryKey
    var id: Int = 0

    var name: String = ""

    var age: Int = 0
}