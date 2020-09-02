package com.mobiledevpro.app.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobiledevpro.app.BuildConfig
import com.mobiledevpro.app.R
import com.mobiledevpro.app.ui.mainscreen.view.MainActivity
import com.mobiledevpro.common.ui.base.BaseActivity
import com.mobiledevpro.common.ui.base.ActivitySettings
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.ref.WeakReference


class SplashActivity : BaseActivity(
    layoutId = R.layout.activity_splash,
    ActivitySettings()
) {

    companion object {
        private const val START_MAIN_SCREEN = 1
        private const val SPLASH_DISPLAY_TIME = 1500L //in milliseconds
    }

    private val handlerOpenNextScreen = Handler(Looper.getMainLooper())


    override fun initToolbar() {
        //no need in this activity
    }

    override fun initViews(layoutView: View) {
        tv_app_version?.text =
            String.format(
                resources.getString(R.string.app_version),
                BuildConfig.VERSION_NAME)

        //start login or main screen (depends on the app logic)
        handlerOpenNextScreen.postDelayed(
            SplashRunnable(this, START_MAIN_SCREEN),
            SPLASH_DISPLAY_TIME
        )
    }

    private class SplashRunnable(activity: AppCompatActivity, val startScreenCode: Int) : Runnable {
        private val activityReference: WeakReference<AppCompatActivity?> = WeakReference(activity)
        override fun run() {
            if (activityReference.get() != null) {
                val activity = activityReference.get()

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
