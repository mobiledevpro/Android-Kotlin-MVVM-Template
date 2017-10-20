package com.mobiledevpro.apptemplate.helper;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mobiledevpro.apptemplate.ui.mainscreen.view.MainFragment;

/**
 * Helper class for work with fragments
 * <p>
 * Created by Dmitriy V. Chernysh on 23.01.17.
 * dmitriy.chernysh@gmail.com
 * <p>
 * www.mobile-dev.pro
 */

public class FragmentsHelper {
    private static final String TAG_FRAGMENT_MAIN = "fragment.main";


    private FragmentsHelper() {
    }


    /**
     * Show Video Form Type chooser dialog
     *
     * @param fm FragmentManager
     */
    public static void showMainFragment(@NonNull FragmentManager fm, @IdRes int containerResId) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setAllowOptimization(false);
        ft.replace(
                containerResId,
                MainFragment.newInstance(),
                TAG_FRAGMENT_MAIN
        ).commit();
    }

}
