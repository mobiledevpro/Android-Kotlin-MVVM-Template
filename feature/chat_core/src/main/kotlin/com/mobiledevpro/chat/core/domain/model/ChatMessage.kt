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
package com.mobiledevpro.chat.core.domain.model

import com.mobiledevpro.chat.core.domain.util.TimeFormat
import com.mobiledevpro.chat.core.domain.util.getTimeString


/**
 * Message model
 *
 * Created on Dec 15, 2020.
 *
 */
data class ChatMessage(
    val uid: String,
    val timeSentUtc: Long, //in ms
    val text: String,
    val user: ChatUser
) {
    fun getFormattedTime() : String = timeSentUtc.getTimeString(TimeFormat.AM_PM)

    fun getAvatarUrl() : String = user.avatarUrl
}