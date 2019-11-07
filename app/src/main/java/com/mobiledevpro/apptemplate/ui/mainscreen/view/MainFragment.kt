package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import com.crashlytics.android.Crashlytics
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.helper.showUserEditFragment
import com.mobiledevpro.apptemplate.helper.showUserViewFragment
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.IMain
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.MainPresenter
import com.mobiledevpro.commons.fragment.BaseFragment

/**
 * Main fragment for main activity
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

class MainFragment : BaseFragment(), IMain.View {

    private lateinit var presenter: IMain.Presenter

    companion object {

        fun newInstance(): MainFragment {

            val args = Bundle()

            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_main


    override fun getOptionsMenuResId(): Int = R.menu.menu_question_notes_list

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.menu_action_note_create -> {
                    Crashlytics.logException(Throwable("Tap on menu Test1"))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun populateView(layoutView: View, savedInstanceState: Bundle?): View {
        ButterKnife.bind(this, layoutView)

        showUserEditLayout()
        showUserViewLayout()

        return layoutView
    }

    override fun initPresenters() {
        presenter = MainPresenter(this)
        lifecycle.addObserver(presenter)
    }

    override fun getAppBarTitle(): Int = R.string.app_name_main

    override fun getHomeAsUpIndicatorIcon(): Int = R.drawable.ic_close_24dp

    override fun showToast(msg: Int) {
        Toast.makeText(
                requireContext(),
                getString(msg),
                Toast.LENGTH_LONG
        ).show()
    }

    private fun showUserEditLayout() {
        showUserEditFragment(childFragmentManager, R.id.fragment_container_one)

    }


    private fun showUserViewLayout() {
        showUserViewFragment(childFragmentManager, R.id.fragment_container_two)
    }
}
