package com.mobiledevpro.app.ui.mainscreen.view

import android.view.MenuItem
import android.widget.Toast
import com.mobiledevpro.app.R
import com.mobiledevpro.app.databinding.FragmentChatPublicBinding
import com.mobiledevpro.app.ui.mainscreen.viewmodel.ChatPublicViewModel
import com.mobiledevpro.common.ui.base.BaseFragment
import com.mobiledevpro.common.ui.base.FragmentSettings
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Main fragment for main activity
 *
 *
 * Created by Dmitriy Chernysh
 *
 * #MobileDevPro
 */

class ChatPublicFragment : BaseFragment<FragmentChatPublicBinding>(
    layoutId = R.layout.fragment_chat_public,
    FragmentSettings(
        statusBarColor = R.color.colorWindowGreyBackground,
        appBarColor = R.color.colorWindowGreyBackground,
        appBarTitle = R.string.app_title_chat_public,
        appBarTitleColor = R.color.colorTextPrimary,
        homeIconId = R.drawable.ic_list_dark_24dp,
        optionsMenuId = R.menu.menu_chat_public,
        homeIconBackPressEnabled = false
    )
) {

    private val viewModel: ChatPublicViewModel by viewModel()

    override fun onInitDataBinding() {
        viewBinding.model = viewModel
        //add lifecycle observer to viewmodel
        lifecycle.addObserver(viewModel)
    }

    override fun observeLifecycleEvents() {

        /*observe(viewModel.editUserButton, observer = {
           // if (it) view?.showEditUserFragment()
        })*/
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(requireActivity(), "Chats list. Not implemented yet", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_action_settings -> {
                Toast.makeText(requireActivity(), "Settings. Not implemented yet", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


}
