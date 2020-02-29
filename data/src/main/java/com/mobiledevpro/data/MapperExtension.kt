package com.mobiledevpro.data

import com.mobiledevpro.domain.model.User
import com.mobiledevpro.local.database.model.UserEntity

/**
 * Extensions for mapping data level models to domain level models and vise versa
 *
 * Created by Dmitriy Chernysh on Feb 28, 2020.
 *
 * http://androiddev.pro
 *
 */

fun UserEntity.toUser(): User = User(
        id = id,
        name = name,
        age = age
)

fun User.toEntity(): UserEntity = UserEntity(
        id = id,
        name = name ?: "",
        age = age
)
