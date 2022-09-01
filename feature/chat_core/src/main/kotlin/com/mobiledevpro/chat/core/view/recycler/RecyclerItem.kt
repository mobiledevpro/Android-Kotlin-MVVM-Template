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
package com.mobiledevpro.chat.core.view.recycler

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.mobiledevpro.chat.core.BR

/**
 * Model for RecyclerView item
 *
 * Created on Dec 14, 2020.
 *
 */
data class RecyclerItem(
    val data: Any?,
    @LayoutRes
    val layoutId: Int,
    val variableId: Int
) {
    fun bind(binding: ViewDataBinding, handler: RecyclerViewHandler?) {
        binding.setVariable(variableId, data)
        handler?.let {
            binding.setVariable(BR.handler, it)
        }

    }
}