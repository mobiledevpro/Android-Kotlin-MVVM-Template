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

fun showEditUserFragment(view: View) {
    view.findNavController()
            .navigate(R.id.action_user_edit)
}