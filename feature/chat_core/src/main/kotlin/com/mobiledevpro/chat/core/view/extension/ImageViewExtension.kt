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

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mobiledevpro.utils.LOG_TAG_DEBUG

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

    @SuppressLint("CheckResult")
    @BindingAdapter(
        value = [
            "bind:imageUrl",
            "bind:isCircle"
        ],
        requireAll = false
    )
    @JvmStatic
    fun ImageView.setImageUrl(imageUri: Uri?, isCircle: Boolean? = true) {
        Glide.with(context)
            .clear(this)

        imageUri ?: return

        Log.d(LOG_TAG_DEBUG, "setImageUrl: $imageUri")
        Log.d(LOG_TAG_DEBUG, "isCircle: $isCircle")

        Glide.with(context)
            .load(imageUri)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply {
                isCircle?.let {
                    Log.d(LOG_TAG_DEBUG, "isCircle: $it")
                    if (it)
                        this.circleCrop()
                }
            }
            .into(this)
    }
}