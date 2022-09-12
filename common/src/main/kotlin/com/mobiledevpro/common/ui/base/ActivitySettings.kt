package com.mobiledevpro.common.ui.base

import androidx.annotation.AnimRes

/**
 * Some settings for Activity
 *
 * Created by Dmitri Chernysh on Sep 03, 2020.
 *
 * http://mobile-dev.pro
 *
 */
data class ActivitySettings(
    @AnimRes
    val openEnterAnimation: Int = 0,
    @AnimRes
    val openExitAnimation: Int = 0,
    @AnimRes
    val closeEnterAnimation: Int = 0,
    @AnimRes
    val closeExitAnimation: Int = 0,
    val isAdjustFontScaleToNormal: Boolean = false,
    val windowFlags: List<Int> = emptyList()
)