package com.mobiledevpro.app.ui.mainscreen.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentMainBinding
import com.mobiledevpro.app.helper.showEditUserFragment
import com.mobiledevpro.app.ui.mainscreen.viewmodel.MainViewModel
import com.mobiledevpro.commons.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


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

    private val viewModel: MainViewModel by viewModel()

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
        //do nothing
    }

}
