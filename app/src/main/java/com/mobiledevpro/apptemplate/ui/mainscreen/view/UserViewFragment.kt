package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.IUserView
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.UserViewPresenter
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

    private lateinit var butterKnife: Unbinder
    private lateinit var presenter: IUserView.Presenter

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
        butterKnife = ButterKnife.bind(this, view)
        return view
    }

    override fun initPresenters() {
        presenter = UserViewPresenter(this)
    }

    override fun onDestroy() {
        butterKnife.unbind()
        super.onDestroy()
    }


}