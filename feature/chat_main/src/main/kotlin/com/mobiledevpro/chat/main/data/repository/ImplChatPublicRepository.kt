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
package com.mobiledevpro.chat.main.data.repository

import android.util.Log
import androidx.core.net.toUri
import com.mobiledevpro.chat.core.data.model.ChatMessageData
import com.mobiledevpro.chat.core.data.model.ChatUserData
import com.mobiledevpro.utils.LOG_TAG_DEBUG
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*


class ImplChatPublicRepository : ChatPublicRepository {

    override fun getMessagesList(user: ChatUserData): Flow<List<ChatMessageData>> =
        getFakeMessagesList()

    private fun getFakeMessagesList(): Flow<List<ChatMessageData>> =
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

            ChatMessageData(
                UUID.randomUUID().toString(),
                Date().time,
                "Wuz Up! Lorem Ipsum is simply dummy text of printing",
                userFirst
            )
                .let(messagesList::add)
                .also {
                    emit(messagesList)
                }

            delay(1000)

            Log.d(LOG_TAG_DEBUG, "getFakeMessagesList: Thread : ${Thread.currentThread().name}")

            ChatMessageData(
                UUID.randomUUID().toString(),
                Date().time,
                "How are you? =)",
                userSecond
            )
                .let(messagesList::add)
                .also {
                    emit(messagesList)
                }

            delay(1000)

            ChatMessageData(
                UUID.randomUUID().toString(),
                Date().time,
                "It has survived not only five centuries, but also the leap into electronic typesetting",
                userFirst
            )
                .let(messagesList::add)
                .also {
                    emit(messagesList)
                }

            delay(1000)

            ChatMessageData(
                UUID.randomUUID().toString(),
                Date().time,
                "Contrary to popular belief. is the Lorem Ipsum is not simply then random text",
                userSecond
            )
                .let(messagesList::add)
                .also {
                    emit(messagesList)
                }

            delay(1000)

            //throw RuntimeException("Exception in flow")

            ChatMessageData(
                UUID.randomUUID().toString(),
                Date().time,
                "Hi. I want to see you!",
                userFirst
            )
                .let(messagesList::add)
                .also {
                    emit(messagesList)
                }

            delay(1000)

            ChatMessageData(
                UUID.randomUUID().toString(),
                Date().time,
                "Yeah. Me too. Let's go out",
                userSecond
            )
                .let(messagesList::add)
                .also {
                    emit(messagesList)
                }
        }
}