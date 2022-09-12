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
package com.mobiledevpro.chat.main.domain.usecase

import com.mobiledevpro.chat.core.data.model.ChatMessageData
import com.mobiledevpro.chat.core.domain.model.ChatMessage
import com.mobiledevpro.chat.core.domain.model.ChatUser
import com.mobiledevpro.chat.core.mapper.toData
import com.mobiledevpro.chat.core.mapper.toDomain
import com.mobiledevpro.chat.main.data.repository.ChatPublicRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * Use case to get public chat messages
 *
 * Created on Sep 06, 2022.
 *
 */
class GetPublicChatMessagesUseCase(
    val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val repository: ChatPublicRepository
) : BaseCoroutinesFLowUseCase<List<ChatMessage>, ChatUser>(defaultDispatcher) {

    override fun buildUseCase(params: ChatUser?): Flow<List<ChatMessage>> =
        params?.toData()?.let { user ->
            repository.getMessagesList(user)
                .map(List<ChatMessageData>::toDomain)
        } ?: throw RuntimeException("Unknown chat user")
}