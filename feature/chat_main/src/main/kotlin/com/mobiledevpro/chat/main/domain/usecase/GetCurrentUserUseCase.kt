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

import androidx.core.net.toUri
import com.mobiledevpro.chat.core.domain.model.ChatUser
import com.mobiledevpro.common.ui.coroutines.BaseCoroutinesUseCase
import com.mobiledevpro.common.ui.coroutines.None
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import java.util.*

/**
 * Use case to get a current user (YOU)
 *
 * Created on Sep 08, 2022.
 *
 */
class GetCurrentUserUseCase(
    defaultDispatcher: CoroutineDispatcher,
) : BaseCoroutinesUseCase<ChatUser, None>(defaultDispatcher) {

    override suspend fun buildUseCase(params: None?): ChatUser {

        delay(5000)

        return ChatUser(
            UUID.randomUUID().toString(),
            "Fake Name",
            "https://i.pravatar.cc/128?img=5".toUri(),
            false
        )
    }

}