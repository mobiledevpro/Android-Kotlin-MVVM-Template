package com.mobiledevpro.data.mapper

import com.mobiledevpro.data.model.UserData
import com.mobiledevpro.local.database.model.UserEntity

/**
 * Extensions for mapping data level models to domain level models and vise versa
 *
 * Created by Dmitriy Chernysh on Feb 28, 2020.
 *
 * http://androiddev.pro
 *
 */

fun UserEntity.toUser(): UserData = UserData(
        id = id,
        name = name,
        age = age
)

fun UserData.toEntity(): UserEntity = UserEntity(
        id = id,
        name = name ?: "",
        age = age
)
