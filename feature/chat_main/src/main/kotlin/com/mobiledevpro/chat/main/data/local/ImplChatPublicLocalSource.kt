/*
 * Copyright 2022 | Dmitri Chernysh | https://mobile-dev.pro
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
package com.mobiledevpro.chat.main.data.local

import android.util.Log
import androidx.core.net.toUri
import com.mobiledevpro.chat.core.data.model.ChatMessageData
import com.mobiledevpro.chat.core.data.model.ChatUserData
import com.mobiledevpro.utils.LOG_TAG_DEBUG
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

/**
 *  Local source to get data from database
 *
 * Created on Sep 12, 2022.
 *
 */
class ImplChatPublicLocalSource : ChatPublicLocalSource {

    override fun getFakeMessagesList(chatUser: ChatUserData): Flow<List<ChatMessageData>> =
        flow {
            Log.d(LOG_TAG_DEBUG, "getFakeMessagesList: Thread : ${Thread.currentThread().name}")

            val messagesList = ArrayList<ChatMessageData>()

            val userFirst = ChatUserData(
                UUID.randomUUID().toString(),
                "Fake Name",
                "https://i.pravatar.cc/128?img=5".toUri(),
                false
            )

            val userSecond = ChatUserData(
                UUID.randomUUID().toString(),
                "Fake Name",
                null,
                true
            )

            val messages = listOf(
                "Wuz Up! Lorem Ipsum is simply dummy text of printing",
                "How are you? =)",
                "It has survived not only five centuries, but also the leap into electronic typesetting",
                "Contrary to popular belief. is the Lorem Ipsum is not simply then random text",
                "Hi. I want to see you!",
                "Yeah. Me too. Let's go out"
            )

            messages.forEachIndexed { index, message ->

                ChatMessageData(
                    UUID.randomUUID().toString(),
                    Date().time,
                    message,
                    if (index % 2 == 0) userFirst else userSecond
                )
                    .let(messagesList::add)
                    .also {
                        emit(messagesList)
                    }

                delay(1000)
            }

        }
}