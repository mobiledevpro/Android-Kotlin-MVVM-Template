package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.mobiledevpro.apptemplate.R
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

    @BindView(R.id.tv_user_name)
    lateinit var tvUserName: TextView

    private lateinit var butterKnife: Unbinder
    private lateinit var presenter: IUserView.Presenter

    private lateinit var viewModel: UserDataViewModel

    companion object {
        fun newInstance(): UserViewFragment {

            val args = Bundle()

            val fragment = UserViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders
                .of(activity as FragmentActivity)
                .get(UserDataViewModel::class.java)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_user_view

    override fun populateView(view: View, bundle: Bundle?): View {
        butterKnife = ButterKnife.bind(this, view)
        viewModel.getUserDataObserver().observe(activity as FragmentActivity, Observer {
            tvUserName.text = it
        })

        return view
    }

    override fun initPresenters() {
        presenter = UserViewPresenter(this)
        lifecycle.addObserver(presenter)
    }

    override fun onDestroy() {
        butterKnife.unbind()
        super.onDestroy()
    }


}