package com.mobiledevpro.apptemplate.helper

import androidx.databinding.InverseMethod

/**
 * Converter helper for data binding
 *
 *
 * Created by Dmitriy Chernysh on 11/12/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */

object TypeConverter {

    @InverseMethod("toInt")
    fun toString(value: Int): String = value.toString()

    fun toInt(value: String?): Int {
        return if (!value.isNullOrEmpty()) value.toInt() else 0
    }
}
