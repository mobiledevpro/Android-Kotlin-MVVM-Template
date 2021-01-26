/*
 * Copyright 2020 http://mobile-dev.pro
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
 */

package com.mobiledevpro.common.ui.base

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import com.mobiledevpro.common.ui.R
import com.mobiledevpro.common.ui.extension.applyStatusBarColor
import com.mobiledevpro.common.ui.extension.dpToPx
import com.mobiledevpro.common.ui.extension.getColorCompatible

abstract class BaseActivity(
    @LayoutRes
    private val layoutId: Int,
    private val settings: ActivitySettings
) : AppCompatActivity(), BaseActivityInterface {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private var actionBar: ActionBar? = null

    abstract fun initToolbar()
    abstract fun initViews(layoutView: View)

    override fun onCreate(savedInstanceState: Bundle?) {
        if (settings.isAdjustFontScaleToNormal)
            adjustFontScaleToNormal(resources.configuration)

        //window feature flags
        settings.windowFlags.forEach {
            window.addFlags(it)
        }

        super.onCreate(savedInstanceState)

        //set start activity animation
        if (settings.openEnterAnimation != 0 || settings.openExitAnimation != 0)
            overridePendingTransition(settings.openEnterAnimation, settings.openExitAnimation)

        setContentView(layoutId)
        initToolbar()
        actionBar = supportActionBar


        //populate view
        val layoutView = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
        initViews(layoutView)

        applyWindowInsets(findViewById(android.R.id.content))
    }

    override fun finish() {
        super.finish()
        //finish activity animation
        if (settings.closeEnterAnimation != 0 || settings.closeExitAnimation != 0)
            overridePendingTransition(settings.closeEnterAnimation, settings.closeExitAnimation)
    }

    override fun setAppBarTitle(titleString: String) {
        actionBar?.title = titleString
    }

    override fun setAppBarSubTitle(subTitleString: String) {
        actionBar?.apply {
            val view: View? = findViewById(R.id.appbar)

            view?.apply {
                if (!TextUtils.isEmpty(subTitleString))
                //set appbar min height
                    view.minimumHeight = dpToPx(72f).toInt()
                else
                //remove appbar min height
                    view.minimumHeight = 0
            }

            subtitle = subTitleString
        }
    }

    override fun setStatusBarColor(@ColorRes colorResId: Int) {
        applyStatusBarColor(colorResId)
    }

    override fun setAppBarColor(@ColorRes colorResId: Int) {
        actionBar?.apply {
            setBackgroundDrawable(
                ColorDrawable(
                    getColorCompatible(colorResId)
                )
            )
        }
    }

    override fun setAppBarTitleColor(@ColorRes colorResId: Int) {
        actionBar?.also {
            findViewById<Toolbar>(R.id.toolbar)?.apply {
                this.setTitleTextColor(
                    getColorCompatible(colorResId)
                )
            }
        }
    }

    override fun setAppWindowBackground(@DrawableRes backgroundResId: Int) {
        window?.setBackgroundDrawableResource(backgroundResId)
    }

    override fun setHomeAsUpIndicatorIcon(@DrawableRes drawable: Int) {
        actionBar?.apply {
            if (drawable != 0) {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(drawable)
            } else
                setDisplayHomeAsUpEnabled(false)
        }
    }

    /**
     * Use Window Insets to apply system paddings to this activity
     */
    private fun applyWindowInsets(view: View) {
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

    /**
     * This method helps to ignore font scale value from device settings.
     *
     * NOTE: User has ability to change font scale in the device settings,
     * in this case font will be scaled in the app
     *
     * @param configuration
     */
    @SuppressWarnings("deprecation")
    private fun adjustFontScaleToNormal(configuration: Configuration) {
        val metrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            baseContext.display?.getRealMetrics(metrics)
        else
            windowManager.defaultDisplay.getMetrics(metrics)

        val wm = getSystemService(WINDOW_SERVICE) as WindowManager?
        wm ?: return

        var densityDpiStable = Configuration.DENSITY_DPI_UNDEFINED
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            densityDpiStable = DisplayMetrics.DENSITY_DEVICE_STABLE //480
        }

        //Device may has different screen resolution modes.
        //As example, Samsung S8: 422 in FHD+, 562 in WQHD+
        var xDpi = resources.displayMetrics.xdpi.toInt()

        //round it to bigger value
        if (xDpi > DisplayMetrics.DENSITY_XXHIGH) {
            xDpi = DisplayMetrics.DENSITY_XXXHIGH //640
        } else if (xDpi > DisplayMetrics.DENSITY_XHIGH) {
            xDpi = DisplayMetrics.DENSITY_XXHIGH //480
        } else if (xDpi > DisplayMetrics.DENSITY_HIGH) {
            xDpi = DisplayMetrics.DENSITY_XHIGH //320
        }
        if (xDpi > 0) densityDpiStable = xDpi
        val densityDpiDefault = DisplayMetrics.DENSITY_DEFAULT
        val densityDefault = densityDpiStable.toFloat() / densityDpiDefault

        //ignore system zoom setting, set zoom by default
        //NOTE: the smaller densityDpiStable the zoom is smaller
        configuration.densityDpi = densityDpiStable

        //Set font scale by default
        configuration.fontScale = 1.00.toFloat()
        //ignore system scaling for fonts, set scale factor by default
        metrics.density = densityDefault
        metrics.scaledDensity = configuration.fontScale * densityDefault
        baseContext.resources.updateConfiguration(configuration, metrics)
    }

}