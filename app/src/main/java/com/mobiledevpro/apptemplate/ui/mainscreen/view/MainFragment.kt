package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.crashlytics.android.Crashlytics
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ViewModelFactory
import com.mobiledevpro.apptemplate.databinding.FragmentMainBinding
import com.mobiledevpro.apptemplate.helper.showEditUserFragment
import com.mobiledevpro.apptemplate.ui.mainscreen.viewmodel.MainViewModel
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

class MainFragment : BaseFragment() {

    private lateinit var viewModel: MainViewModel

    override fun getLayoutResId() = R.layout.fragment_main

    override fun getAppBarTitle() = R.string.app_name_main

    override fun getOptionsMenuResId() = R.menu.menu_main_fragment

    override fun getHomeAsUpIndicatorIcon() = R.drawable.ic_close_24dp

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.menu_action_note_create -> {
                    Crashlytics.logException(Throwable("Tap on menu Test1"))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun populateView(view: View, savedInstanceState: Bundle?): View {
        //databinding
        val binding = FragmentMainBinding.bind(view)
                .apply {
                    mainViewModel = viewModel
                }
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.editUserButton.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { b ->
                if (b)
                //go to Edit User fragment
                    view.showEditUserFragment()
            }
        })

        return binding.root
    }

    override fun initPresenters() {
        //init view model instead of presenter
        val app = requireActivity().application
        val viewModelFactory = ViewModelFactory(app)

        //init ViewModel for this fragment
        viewModel = ViewModelProvider(activity as FragmentActivity, viewModelFactory)
                .get(MainViewModel::class.java)
    }

}
