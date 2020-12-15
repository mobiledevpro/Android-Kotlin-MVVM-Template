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
package com.mobiledevpro.chat.core.view.mapper

import com.mobiledevpro.chat.core.BR
import com.mobiledevpro.chat.core.R
import com.mobiledevpro.chat.core.domain.model.ChatMessage
import com.mobiledevpro.chat.core.view.recycler.RecyclerItem

/**
 * Mapper for Presentation layer
 *
 * Created on Dec 15, 2020.
 *
 */


fun <T> List<T>.toRecyclerView(): List<RecyclerItem> =
    this.mapTo(ArrayList<RecyclerItem>()) {
        when (it) {
            is ChatMessage -> it.toRecyclerItem()
            else -> RecyclerItem(null, 0, 0)
        }
    }


fun List<RecyclerItem>.recyclerToList(): List<*> =
    this.mapTo(ArrayList<Any>()) {
        when (it.data) {
            is ChatMessage -> {
                it.data
            }
            else -> emptyList<Any>()
        }
    }

fun <T> T.toRecyclerItem() = when (this) {
    is ChatMessage -> RecyclerItem(
        data = this,
        layoutId = if (this.user.isItYou)
            R.layout.item_chat_message_send
        else
            R.layout.item_chat_message_received,
        variableId = BR.message
    )
    else -> throw Throwable("Type $this is not defined in Mapper.toRecyclerItem() function")
}