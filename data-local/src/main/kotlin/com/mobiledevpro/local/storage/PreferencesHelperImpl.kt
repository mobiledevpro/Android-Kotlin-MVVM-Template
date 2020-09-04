package com.mobiledevpro.local.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * Preferences Helper
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 */

class PreferencesHelperImpl(private val appContext: Context) : PreferencesHelper {

    companion object {
        const val KEY_PREFS_FILE_MAIN = "app.main.preferences"
        const val KEY_SOMETHING = "something"
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
