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
package com.mobiledevpro.chat.main.domain.interactor

import com.mobiledevpro.chat.core.domain.model.ChatMessage
import com.mobiledevpro.chat.main.domain.usecase.GetCurrentUserUseCase
import com.mobiledevpro.chat.main.domain.usecase.GetPublicChatMessagesUseCase
import com.mobiledevpro.common.ui.coroutines.andThenFlow
import kotlinx.coroutines.flow.Flow

/**
 * Interactor uses in Public chat View Model
 *
 * Created on Dec 15, 2020.
 *
 */
class ImplChatPublicInteractor(
    private val getPublicChatMessagesUseCase: GetPublicChatMessagesUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ChatPublicInteractor {

    override suspend fun getMessagesList(): Flow<Result<List<ChatMessage>>> =
        getCurrentUserUseCase.execute()
            .andThenFlow(getPublicChatMessagesUseCase::execute)

}