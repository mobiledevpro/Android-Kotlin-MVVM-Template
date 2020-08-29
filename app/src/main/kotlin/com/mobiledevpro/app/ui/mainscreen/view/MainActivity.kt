package com.mobiledevpro.app.ui.mainscreen.view

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import com.mobiledevpro.app.R
import com.mobiledevpro.commons.activity.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.activity_main

    override fun isAdjustFontScaleToNormal() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        //set navigation bar translucent
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        super.onCreate(savedInstanceState)

        applyWindowInsets(findViewById(android.R.id.content))
    }

    override fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        toolbar?.let { setSupportActionBar(it) }
    }

    override fun initPresenters() {
        //no need
    }

    override fun populateView(layoutView: View) {
        //work with view

    }

    private fun applyWindowInsets(view: View) {
        //Use Window Insets to set top and bottom paddings to our activity
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            v.updatePadding(
                    left = insets.systemWindowInsetLeft,
                    top = insets.systemWindowInsetTop,
                    right = insets.systemWindowInsetRight,
                    bottom = insets.systemWindowInsetBottom
            )
            insets
        }
    }
}
