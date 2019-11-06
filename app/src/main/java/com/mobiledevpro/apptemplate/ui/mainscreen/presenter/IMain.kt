package com.mobiledevpro.apptemplate.ui.mainscreen.presenter

/**
 * Interface for main screen
 *
 *
 * Created by Dmitriy V. Chernysh
 * dmitriy.chernysh@gmail.com
 *
 * https://instagr.am/mobiledevpro/
 *
 * #MobileDevPro
 */

interface IMain {
    interface View {
        //  val activity : Activity
    }

    interface Presenter {

        fun bindView(view: View)

        fun unbindView()
    }
}
