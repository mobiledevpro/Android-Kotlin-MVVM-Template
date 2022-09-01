package com.mobiledevpro.app.ui.mainscreen.view

import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.WindowCompat
import com.google.android.material.appbar.AppBarLayout
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.ActivityMainBinding
import com.mobiledevpro.common.ui.base.ActivitySettings
import com.mobiledevpro.common.ui.base.BaseActivity
import com.mobiledevpro.common.ui.extension.getColorCompatible

class MainActivity : BaseActivity(
    layoutId = R.layout.activity_main,
    ActivitySettings(
        isAdjustFontScaleToNormal = true
    )
), AppbarAnimation {

    private lateinit var viewBinding: ActivityMainBinding
    private var motionAppBarLayout: MotionLayout? = null


    override fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        toolbar?.let {
            setSupportActionBar(it)
        }
/*
        window.apply {
            getColorCompatible(android.R.color.transparent)
                .let { color ->
                    navigationBarColor = color
                   // statusBarColor = color
                }
        }

 */
    }

    override fun initViews(layoutView: View) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        viewBinding = ActivityMainBinding.bind(layoutView)

        //do something: as example, init bottom navigation.

        val appBarLayout: AppBarLayout = viewBinding.appbar
        motionAppBarLayout = viewBinding.appbarMotion


        /*
        val listener = AppBarLayout.OnOffsetChangedListener { unused, verticalOffset ->
            Log.d("TEST", "AppBarLayout Offset: $verticalOffset")
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()

            Log.d("TEST", "AppBarLayout seekPosition: $seekPosition")
           // motionLayout.progress = seekPosition
        }

        appBarLayout.addOnOffsetChangedListener(listener)

*/

        var toogle = true;

        viewBinding.toolbar.setOnClickListener {
            Toast.makeText(this, "Toolbar clicked", Toast.LENGTH_SHORT).show()
            toogle = if (toogle) {
                motionAppBarLayout?.transitionToEnd()
                false
            } else {
                motionAppBarLayout?.transitionToStart()
                true
            }
        }
    }

    override fun setAppBarTitle(titleString: String) {
        supportActionBar?.apply {
            //custom title uses instead of default
            setDisplayShowTitleEnabled(false)
            viewBinding.toolbarTitle.text = titleString
        }
    }

    override fun setAppBarTitleColor(colorResId: Int) {
        supportActionBar?.apply {
            //custom title uses instead of default
            setDisplayShowTitleEnabled(false)
            viewBinding.toolbarTitle.setTextColor(
                getColorCompatible(colorResId)
            )
        }
    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            RNav.id.menu_action_settings -> {
                Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
                motionAppBarLayout?.apply {
                    /*  transitionToEnd {
                          launch(R.id.fragment_nav_host, Navigation(NavigateTo.PROFILE_SETTINGS))
                      }*/
                }

                //TODO: navigate to profile screen
                launch(R.id.fragment_nav_host, Navigation(NavigateTo.PROFILE_SETTINGS))


                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

     */

    override fun onBackPressed() {
      motionAppBarLayout?.transitionToStart()

    }

    override fun onAppbarCollapse() {
        motionAppBarLayout?.transitionToStart()
    }

    override fun onAppbarExpand() {
        motionAppBarLayout?.transitionToEnd()
    }
}
