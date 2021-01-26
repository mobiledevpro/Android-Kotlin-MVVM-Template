package com.mobiledevpro.app.ui.mainscreen.view

import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.mobiledevpro.app.R
import com.mobiledevpro.common.ui.base.ActivitySettings
import com.mobiledevpro.common.ui.base.BaseActivity
import com.mobiledevpro.common.ui.extension.getColorCompatible

class MainActivity : BaseActivity(
    layoutId = R.layout.activity_main,
    ActivitySettings(
        isAdjustFontScaleToNormal = true,
        windowFlags = listOf(
            //set navigation bar translucent
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )
    )
) {

    override fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        toolbar?.let {
            setSupportActionBar(it)
        }

    }

    override fun initViews(layoutView: View) {
        //do something: as example, init bottom navigation.
    }

    override fun setAppBarTitle(titleString: String) {
        supportActionBar?.apply {
            //custom title uses instead of default
            setDisplayShowTitleEnabled(false)
            findViewById<TextView>(R.id.toolbar_title)?.text = titleString
        }
    }

    override fun setAppBarTitleColor(colorResId: Int) {
        supportActionBar?.apply {
            //custom title uses instead of default
            setDisplayShowTitleEnabled(false)
            findViewById<TextView>(R.id.toolbar_title)?.setTextColor(
                getColorCompatible(colorResId)
            )
        }
    }
}
