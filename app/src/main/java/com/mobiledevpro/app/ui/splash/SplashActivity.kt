package com.mobiledevpro.app.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import com.mobiledevpro.app.BuildConfig
import com.mobiledevpro.app.R
import com.mobiledevpro.app.ui.mainscreen.view.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.ref.WeakReference


class SplashActivity : AppCompatActivity() {

    companion object {
        private const val START_MAIN_SCREEN = 1
        private const val SPLASH_DISPLAY_TIME = 1500L //in milliseconds
    }

    private val mStartNextActivityHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        applyWindowInsets(findViewById(android.R.id.content))

        tv_app_version.text =
                String.format(resources.getString(R.string.app_version), BuildConfig.VERSION_NAME)


        //start login or main screen (depends on the app logic)
        mStartNextActivityHandler.postDelayed(
                SplashRunnable(this, START_MAIN_SCREEN),
                SPLASH_DISPLAY_TIME
        )

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

    private class SplashRunnable(activity: AppCompatActivity, val startScreenCode: Int) : Runnable {
        private val mActivityReference: WeakReference<AppCompatActivity?> = WeakReference(activity)
        override fun run() {
            if (mActivityReference.get() != null) {
                val activity = mActivityReference.get()

                val intent: Intent = if (startScreenCode == START_MAIN_SCREEN) {
                    Intent(activity, MainActivity::class.java)
                } else {
                    //other activity
                    // Intent(activity, LoginActivity::class.java)
                    Intent(activity, MainActivity::class.java)
                }
                activity!!.startActivity(intent)
                activity.finish()
            }
        }
    }


}
