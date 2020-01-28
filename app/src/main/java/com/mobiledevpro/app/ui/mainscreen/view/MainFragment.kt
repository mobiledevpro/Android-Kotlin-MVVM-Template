package com.mobiledevpro.app.ui.mainscreen.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobiledevpro.app.R
import com.mobiledevpro.app.ViewModelFactory
import com.mobiledevpro.app.databinding.FragmentMainBinding
import com.mobiledevpro.app.helper.showEditUserFragment
import com.mobiledevpro.app.ui.mainscreen.viewmodel.MainViewModel
import com.mobiledevpro.commons.fragment.BaseFragment

/**
 * Main fragment for main activity
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro/
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
                R.id.menu_action_crash -> {
                    throw RuntimeException("Test crash")
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
