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
package com.mobiledevpro.chat.core.view.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Extension
 *
 * Created on Dec 22, 2020.
 *
 */

/*
* It uses for DataBinding
*
* NOTE: it has to be placed in the same module where layouts are
 */
object ImageViewExtension {

    @BindingAdapter(
        value = [
            "bind:imageUrl",
            "bind:isCircle"
        ],
        requireAll = false
    )
    @JvmStatic
    fun ImageView.setImageUrl(imageUrl: String, isCircle: Boolean?) {

        if (imageUrl.isEmpty()) return

        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply {
                if (isCircle != false)
                    circleCrop()
            }
            .into(this)
    }
}