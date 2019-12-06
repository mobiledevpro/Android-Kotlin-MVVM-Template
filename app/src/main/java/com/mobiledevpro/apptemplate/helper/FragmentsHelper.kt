package com.mobiledevpro.apptemplate.helper

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ui.mainscreen.view.MainFragment
import com.mobiledevpro.apptemplate.ui.mainscreen.view.UserEditFragment
import com.mobiledevpro.apptemplate.ui.mainscreen.view.UserViewFragment

/**
 * Helper class for work with fragments
 *
 *
 * Created by Dmitriy V. Chernysh
 * dmitriy.chernysh@gmail.com
 *
 *
 * https://instagr.am/mobiledevpro/
 *
 *
 * #MobileDevPro
 */

private const val TAG_FRAGMENT_MAIN = "fragment.main"
private const val TAG_FRAGMENT_USER_EDIT = "fragment.user.edit"
private const val TAG_FRAGMENT_USER_VIEW = "fragment.user.view"


fun showMainFragment(fm: FragmentManager, @IdRes containerResId: Int) {
    val ft = fm.beginTransaction()
    ft.setAllowOptimization(false)
    ft.replace(
        containerResId,
        MainFragment.newInstance(),
        TAG_FRAGMENT_MAIN
    ).commit()
}

fun showUserEditFragment(fm: FragmentManager, @IdRes containerResId: Int) {
    val ft = fm.beginTransaction()
    ft.setAllowOptimization(false)
    ft.replace(
            containerResId,
            UserEditFragment.newInstance(),
            TAG_FRAGMENT_USER_EDIT
    ).commit()
}

fun showUserViewFragment(fm: FragmentManager, @IdRes containerResId: Int) {
    val ft = fm.beginTransaction()
    ft.setAllowOptimization(false)
    ft.replace(
            containerResId,
            UserViewFragment.newInstance(),
            TAG_FRAGMENT_USER_VIEW
    ).commit()
}

/**
 * Show fragment for customers list
 *
 * @param fm FragmentManager
 */
fun showMainFragmentWithAnimation(
    fm: FragmentManager,
    @IdRes containerResId: Int,
    addToBackStack: Boolean
) {
    val ft = fm.beginTransaction()
    ft.setAllowOptimization(false)

    if (addToBackStack) {
        ft.setCustomAnimations(
            R.anim.base_anim_fragment_enter_slide_left,
            R.anim.base_anim_fragment_exit_slide_right,
            R.anim.base_anim_fragment_pop_enter_slide_right,
            R.anim.base_anim_fragment_pop_exit_slide_left
        )
        ft.addToBackStack(TAG_FRAGMENT_MAIN)
    } else {
        ft.setCustomAnimations(
            0,
            R.anim.base_anim_fragment_exit_slide_right,
            R.anim.base_anim_fragment_pop_enter_slide_right,
            0
        )
    }

    ft.replace(
        containerResId,
        MainFragment.newInstance(),
        TAG_FRAGMENT_MAIN
    ).commit()
}
