package com.mobiledevpro.app.ui.mainscreen.view


import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentMainBinding
import com.mobiledevpro.app.helper.showEditUserFragment
import com.mobiledevpro.app.ui.mainscreen.viewmodel.MainViewModel
import com.mobiledevpro.common.ui.base.BaseFragment
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
    appBarTitleId = R.string.app_name_main,
    optionsMenuId = R.menu.menu_main_fragment,
    homeIconId = R.drawable.ic_close_24dp
) {

    private val viewModel : MainViewModel by viewModel()

    override fun onInitDataBinding() {
        viewBinding.model = viewModel
    }

    override fun observeLifecycleEvents() {

        observe(viewModel.editUserButton, observer = {
            if (it) view?.showEditUserFragment()
        })
    }


    // private val viewModel: MainViewModel by viewModel()
    /*

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


        viewModel.editUserButton.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { b ->
                if (b)
                //go to Edit User fragment
                    view.showEditUserFragment()
            }
        })

        return binding.root
    }


     */
}
