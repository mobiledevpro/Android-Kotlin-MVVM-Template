package com.mobiledevpro.domain.mapper

import com.mobiledevpro.data.model.UserData
import com.mobiledevpro.domain.model.User


fun User.toData() = UserData(
    id,
    name,
    age
)

fun UserData.toDomain() = User(
    id,
    name,
    age
)