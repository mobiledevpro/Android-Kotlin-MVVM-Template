package com.mobiledevpro.chat.core.data.model

import android.net.Uri

/**
 * Data layer
 *
 * Created on Sep 09, 2022.
 *
 */
data class ChatUserData(
    val uid: String,
    val name: String,
    val avatarUrl: Uri?,
    val isItYou: Boolean
)
