package com.mobiledevpro.apptemplate.helper

import android.view.View
import androidx.navigation.findNavController
import com.mobiledevpro.apptemplate.R

/**
 * Navigation Helper
 *
 * Created by Dmitriy Chernysh on 12/7/19.
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */

fun View.showEditUserFragment() {
    this.findNavController()
            .navigate(R.id.actionUserEdit)
}


fun View.showViewUserFragment() {
    this.findNavController()
            .navigate(R.id.actionUserView)
}