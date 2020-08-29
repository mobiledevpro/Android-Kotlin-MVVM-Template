package com.mobiledevpro.domain.model

/**
 * User model for domain level
 *
 * Created by Dmitriy Chernysh on Feb 28, 2020.
 *
 * http://androiddev.pro
 *
 */
data class User(
        var id: Int = 0,
        var name: String? = null,
        var age: Int = 0
)