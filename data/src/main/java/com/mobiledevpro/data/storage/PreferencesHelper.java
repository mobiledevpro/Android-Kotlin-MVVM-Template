package com.mobiledevpro.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preferences Helper
 * <p>
 * Created by Dmitriy V. Chernysh on 25.01.17.
 * dmitriy.chernysh@gmail.com
 * <p>
 * www.mobile-dev.pro
 */

public class PreferencesHelper {
    private static final String KEY_PREFS_FILE_MAIN = "app.main.preferences";
    private static final String KEY_SOMETHING = "something";

    private static PreferencesHelper sHelper;

    private SharedPreferences mMainPrefs;

    public static PreferencesHelper getInstance(Context appContext) {
        if (sHelper == null) {
            sHelper = new PreferencesHelper(appContext);
        }
        return sHelper;
    }

    private PreferencesHelper(Context appContext) {
        mMainPrefs = appContext.getSharedPreferences(KEY_PREFS_FILE_MAIN, Context.MODE_PRIVATE);
    }

    /**
     * Save something
     *
     * @param somethingValue Something value
     */
    public void setSomething(String somethingValue) {
        SharedPreferences.Editor editor = mMainPrefs.edit();
        editor.putString(KEY_SOMETHING, somethingValue);
        editor.apply();
    }


    /**
     * Get something
     *
     * @return Something
     */
    public String getSomething() {
        return mMainPrefs.getString(KEY_SOMETHING, "");
    }


}
