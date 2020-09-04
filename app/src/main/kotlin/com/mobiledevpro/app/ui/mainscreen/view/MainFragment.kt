package com.mobiledevpro.app.ui.mainscreen.view


import android.view.MenuItem
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentMainBinding
import com.mobiledevpro.app.extension.showEditUserFragment
import com.mobiledevpro.app.ui.mainscreen.viewmodel.MainViewModel
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
import com.mobiledevpro.common.ui.extension.observe
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Main fragment for main activity
 *
 *
 * Created by Dmitriy Chernysh
 *
 * #MobileDevPro
 */

class MainFragment : BaseFragment<FragmentMainBinding>(
    layoutId = R.layout.fragment_main,
    FragmentSettings(
        appBarTitle = R.string.app_name_main,
        optionsMenuId = R.menu.menu_main_fragment,
        homeIconId = R.drawable.ic_close_24dp
    )
) {

     private val viewModel: MainViewModel by viewModel()

    override fun onInitDataBinding() {
        viewBinding.model = viewModel
        //add lifecycle observer to viewmodel
        lifecycle.addObserver(viewModel)
    }

    override fun observeLifecycleEvents() {

        observe(viewModel.editUserButton, observer = {
            if (it) view?.showEditUserFragment()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.menu_action_crash -> {
                throw RuntimeException("Test crash")
            }
            else -> super.onOptionsItemSelected(item)
        }
}
