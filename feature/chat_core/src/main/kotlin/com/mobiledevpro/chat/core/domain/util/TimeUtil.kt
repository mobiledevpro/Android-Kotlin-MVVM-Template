/*
 * Copyright 2020 | Dmitri Chernysh | http://mobile-dev.pro
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.mobiledevpro.chat.core.domain.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Util class to format date and time
 *
 * Created on Dec 22, 2020.
 *
 */

enum class TimeFormat {
    AM_PM
}

private object TimePattern {
    const val amPm = "h:mm a"
}

fun Long.getTimeString(format: TimeFormat): String {
    val date = Date(this)
    var formatter: SimpleDateFormat

    return when (format) {
        TimeFormat.AM_PM -> {
            formatter = SimpleDateFormat(TimePattern.amPm, Locale.getDefault()).apply {
                timeZone = Calendar.getInstance().timeZone
            }
            formatter.format(date)

        }
        else -> ""
    }
}