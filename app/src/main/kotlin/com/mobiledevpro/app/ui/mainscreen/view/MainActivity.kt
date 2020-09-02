package com.mobiledevpro.app.ui.mainscreen.view

import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import com.mobiledevpro.app.R
import com.mobiledevpro.common.ui.base.BaseActivity
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(
    layoutId = R.layout.activity_main,
    isAdjustFontScaleToNormal = true,
    windowFlags = listOf(
        //set navigation bar translucent
        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
    ),
    closeEnterAnimation = 0,
    closeExitAnimation = 0,
    openEnterAnimation = 0,
    openExitAnimation = 0
) {

    override fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        toolbar?.let { setSupportActionBar(it) }
    }

    override fun initViews(layoutView: View) {
        //do something: as example, init bottom navigation.
    }

}
