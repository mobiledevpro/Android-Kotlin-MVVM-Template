package com.mobiledevpro.apptemplate.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mobiledevpro.apptemplate.BuildConfig
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ui.mainscreen.view.MainActivity
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

        tv_app_version.text =
                String.format(resources.getString(R.string.app_version), BuildConfig.VERSION_NAME)


        //start login or main screen (depends on the app logic)
        mStartNextActivityHandler.postDelayed(
                SplashRunnable(this, START_MAIN_SCREEN),
                SPLASH_DISPLAY_TIME
        )

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
