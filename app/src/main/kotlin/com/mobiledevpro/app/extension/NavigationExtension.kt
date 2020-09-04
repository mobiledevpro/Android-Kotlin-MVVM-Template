package com.mobiledevpro.app.extension

import android.view.View
import androidx.navigation.findNavController
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

fun View.showEditUserFragment() {
    this.findNavController()
            .navigate(R.id.action_to_user_edit)
}


fun View.showViewUserFragment() {
    this.findNavController()
            .navigate(R.id.action_to_user_view)
}