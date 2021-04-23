package com.mobiledevpro.common.ui.base

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.annotation.TransitionRes

/**
 * Some settings for Fragment
 *
 * Created by Dmitri Chernysh on Sep 03, 2020.
 *
 * http://mobile-dev.pro
 *
 */
data class FragmentSettings(
    val appBarTitle: Any = 0,
    val appBarSubTitle: Any = 0,
    @MenuRes
    val optionsMenuId: Int = 0,
    @DrawableRes
    val homeIconId: Int = 0,
    @ColorRes
    val statusBarColor: Int = 0,
    @ColorRes
    val appBarColor: Int = 0,
    @ColorRes
    val appBarTitleColor: Int = 0,
    @DrawableRes
    val appWindowBackground: Int = 0,
    val homeIconBackPressEnabled: Boolean = true,
    @TransitionRes
    val enterTransition : Int = 0,
    @TransitionRes
    val exitTransition : Int = 0
)
