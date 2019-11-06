package com.mobiledevpro.data.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * Preferences Helper
 *
 *
 * Created by Dmitriy V. Chernysh on 25.01.17.
 * dmitriy.chernysh@gmail.com
 *
 *
 * www.mobile-dev.pro
 */

class PreferencesHelper
private constructor(appContext: Context) {

    companion object {
        const val KEY_PREFS_FILE_MAIN = "app.main.preferences"
        const val KEY_SOMETHING = "something"

        private var sHelper: PreferencesHelper? = null

        fun getInstance(appContext: Context): PreferencesHelper {
            if (sHelper == null) {
                sHelper = PreferencesHelper(appContext)
            }
            return sHelper as PreferencesHelper
        }
    }

    private val mMainPrefs: SharedPreferences

    init {
        mMainPrefs = appContext.getSharedPreferences(KEY_PREFS_FILE_MAIN, Context.MODE_PRIVATE)
    }

    /**
     * Set and get something from SharedPreferences
     *
     */
    var something: String?
        get() = mMainPrefs.getString(KEY_SOMETHING, "")
        set(somethingValue) {
            val editor = mMainPrefs.edit()
            editor.putString(KEY_SOMETHING, somethingValue)
            editor.apply()
        }

}
