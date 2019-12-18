package com.mobiledevpro.apptemplate.ui.userviewscreen.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ViewModelFactory
import com.mobiledevpro.apptemplate.databinding.FragmentUserViewBinding
import com.mobiledevpro.apptemplate.ui.mainscreen.viewmodel.UserDataViewModel
import com.mobiledevpro.apptemplate.ui.userviewscreen.presenter.IUserViewPresenter
import com.mobiledevpro.apptemplate.ui.userviewscreen.presenter.UserViewPresenter
import com.mobiledevpro.commons.fragment.BaseFragment

/**
 * Fragment for UserView screen
 *
 *
 * Created by Dmitriy Chernysh on 11/7/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class UserViewFragment : BaseFragment() {

    private lateinit var userViewModel: UserDataViewModel
    private lateinit var presenter: IUserViewPresenter

    override fun getLayoutResId() = R.layout.fragment_user_view

    override fun getAppBarTitle() = R.string.appbar_title_edit_view

    override fun getHomeAsUpIndicatorIcon() = R.drawable.ic_arrow_back_white_24dp

    override fun populateView(view: View, bundle: Bundle?): View {
        val binding = FragmentUserViewBinding.bind(view)
                .apply {
                    userDataModel = userViewModel
                }
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }

    override fun initPresenters() {
        //init view model instead of presenter
        val app = requireActivity().application
        val viewModelFactory = ViewModelFactory(app)

        //init ViewModel for this fragment
        userViewModel = ViewModelProvider(activity as FragmentActivity, viewModelFactory)
                .get(UserDataViewModel::class.java)

        //init presenter
        presenter = UserViewPresenter(userViewModel)
        //add lifecycle observer to presenter
        lifecycle.addObserver(presenter)
    }

}