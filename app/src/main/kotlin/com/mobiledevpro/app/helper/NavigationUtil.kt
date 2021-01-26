package com.mobiledevpro.app.helper

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobiledevpro.app.R

/**
 * Navigation Helper
 *
 * Created by Dmitriy Chernysh
 *
 * http://mobile-dev.pro
 *
 * #MobileDevPro
 */


fun Fragment.showProfileSettingsFragment() {
    this.findNavController()
        .navigate(R.id.actionShowProfileSettings)
}

/*
fun View.showViewUserFragment() {
    this.findNavController()
            .navigate(R.id.action_to_user_view)
}
*/