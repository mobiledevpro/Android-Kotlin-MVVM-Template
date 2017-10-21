package com.mobiledevpro.apptemplate.helper;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mobiledevpro.apptemplate.R;
import com.mobiledevpro.apptemplate.ui.mainscreen.view.MainFragment;

/**
 * Helper class for work with fragments
 * <p>
 * Created by Dmitriy V. Chernysh
 * dmitriy.chernysh@gmail.com
 * <p>
 * https://fb.me/mobiledevpro/
 * <p>
 * #MobileDevPro
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

    /**
     * Show fragment for customers list
     *
     * @param fm FragmentManager
     */
    public static void showMainFragmentWithAnimation(FragmentManager fm, @IdRes int containerResId, boolean addToBackStack) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.setAllowOptimization(false);

        if (addToBackStack) {
            ft.setCustomAnimations(
                    R.anim.base_anim_fragment_enter_slide_left,
                    R.anim.base_anim_fragment_exit_slide_right,
                    R.anim.base_anim_fragment_pop_enter_slide_right,
                    R.anim.base_anim_fragment_pop_exit_slide_left
            );
            ft.addToBackStack(TAG_FRAGMENT_MAIN);
        } else {
            ft.setCustomAnimations(
                    0,
                    R.anim.base_anim_fragment_exit_slide_right,
                    R.anim.base_anim_fragment_pop_enter_slide_right,
                    0
            );
        }

        ft.replace(
                containerResId,
                MainFragment.newInstance(),
                TAG_FRAGMENT_MAIN
        ).commit();
    }
}
