package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.databinding.FragmentUserViewBinding
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.IUserView
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.UserViewPresenter
import com.mobiledevpro.apptemplate.uimodel.UserDataViewModel
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
class UserViewFragment : BaseFragment(), IUserView.View {

    private lateinit var presenter: IUserView.Presenter

    private lateinit var userViewModel: UserDataViewModel
    private lateinit var binding: FragmentUserViewBinding

    companion object {
        fun newInstance(): UserViewFragment {

            val args = Bundle()

            val fragment = UserViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_user_view

    override fun populateView(view: View, bundle: Bundle?): View {
        binding = FragmentUserViewBinding.bind(view)
                .apply {
                    userDataModel = userViewModel
                }
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }

    override fun initPresenters() {
        userViewModel = ViewModelProvider(activity as FragmentActivity)
                .get(UserDataViewModel::class.java)

        presenter = UserViewPresenter(this)
        lifecycle.addObserver(presenter)
    }

}