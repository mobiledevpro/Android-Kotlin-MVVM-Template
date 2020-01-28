package com.mobiledevpro.app.helper

import androidx.databinding.InverseMethod

/**
 * Converter helper for data binding
 *
 *
 * Created by Dmitriy Chernysh
 *
 *
 * http://androiddev.pro
 *
 * #MobileDevPro
 */

object TypeConverter {

    @InverseMethod("toInt")
    fun toString(value: Int): String = value.toString()

    fun toInt(value: String?): Int {
        return if (!value.isNullOrEmpty()) value.toInt() else 0
    }
}
