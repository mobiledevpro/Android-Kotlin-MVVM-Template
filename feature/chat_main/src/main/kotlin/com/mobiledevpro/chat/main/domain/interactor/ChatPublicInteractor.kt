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
import com.mobiledevpro.rx.RxResult
import io.reactivex.Observable

/**
 * Interactor uses in Public chat View Model
 *
 * Created on Dec 15, 2020.
 *
 */
interface ChatPublicInteractor {

    fun getMessagesList(userUid : String) : Observable<RxResult<List<ChatMessage>>>

}