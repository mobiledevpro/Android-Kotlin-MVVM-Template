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

import androidx.core.net.toUri
import com.mobiledevpro.chat.core.domain.model.ChatMessage
import com.mobiledevpro.chat.core.domain.model.ChatUser
import com.mobiledevpro.rx.RxResult
import com.mobiledevpro.rx.toViewResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

/**
 * Interactor uses in Public chat View Model
 *
 * Created on Dec 15, 2020.
 *
 */
class ImplChatPublicInteractor(

) : ChatPublicInteractor {

    override fun getMessagesList(userUid: String): Observable<RxResult<List<ChatMessage>>> =
        getFakeMessagesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toViewResult()

    private fun getFakeMessagesList(): Observable<List<ChatMessage>> =
        Observable.create { emitter ->
            if (emitter.isDisposed) return@create

            val list = ArrayList<ChatMessage>()

            val pause: () -> Unit = {
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {

                }
            }

            pause()

            if (emitter.isDisposed)
                return@create
            else
                emitter.onNext(list)

            val userSome = ChatUser(
                UUID.randomUUID().toString(),
                "Fake Name",
                "https://i.pravatar.cc/128?img=5".toUri(),
                false
            )

            val userYou = ChatUser(
                UUID.randomUUID().toString(),
                "Fake Name",
                null,
                true
            )

            var chatMessage = ChatMessage(
                UUID.randomUUID().toString(),
                Date().time,
                "Wuz Up! Lorem Ipsum is simply dummy text of printing",
                userSome
            )
            list.add(chatMessage)

            pause()

            if (emitter.isDisposed)
                return@create
            else
                emitter.onNext(list)


            chatMessage = ChatMessage(
                UUID.randomUUID().toString(),
                Date().time,
                "How are you? =)",
                userYou
            )
            list.add(chatMessage)

            pause()

            if (emitter.isDisposed)
                return@create
            else
                emitter.onNext(list)

            chatMessage = ChatMessage(
                UUID.randomUUID().toString(),
                Date().time,
                "It has survived not only five centuries, but also the leap into electronic typesetting",
                userSome
            )
            list.add(chatMessage)

            pause()

            if (emitter.isDisposed)
                return@create
            else
                emitter.onNext(list)

            chatMessage = ChatMessage(
                UUID.randomUUID().toString(),
                Date().time,
                "Contrary to popular belief. is the Lorem Ipsum is not simply then random text",
                userYou
            )
            list.add(chatMessage)

            pause()

            if (emitter.isDisposed)
                return@create
            else
                emitter.onNext(list)

            chatMessage = ChatMessage(
                UUID.randomUUID().toString(),
                Date().time,
                "Hi. I want to see you!",
                userSome
            )
            list.add(chatMessage)

            pause()

            if (emitter.isDisposed)
                return@create
            else
                emitter.onNext(list)

            chatMessage = ChatMessage(
                UUID.randomUUID().toString(),
                Date().time,
                "Yeah. Me too. Let's go out",
                userYou
            )
            list.add(chatMessage)

            pause()

            if (emitter.isDisposed)
                return@create
            else
                emitter.onNext(list)
        }
}