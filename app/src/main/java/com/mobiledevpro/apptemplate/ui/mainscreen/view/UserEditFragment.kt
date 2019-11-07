package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.IUserEdit
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.UserEditPresenter
import com.mobiledevpro.commons.fragment.BaseFragment

/**
 * Fragment for UserEdit screen
 *
 *
 * Created by Dmitriy Chernysh on 11/7/19.
 *
 *
 * https://instagr.am/mobiledevpro
 * #MobileDevPro
 */
class UserEditFragment : BaseFragment(), IUserEdit.View {

    @BindView(R.id.edt_name)
    lateinit var edtUserName: EditText

    private lateinit var butterKnife: Unbinder
    private lateinit var presenter: IUserEdit.Presenter

    companion object {
        fun newInstance(): UserEditFragment {

            val args = Bundle()

            val fragment = UserEditFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_user_edit

    override fun populateView(view: View, bundle: Bundle?): View {
        butterKnife = ButterKnife.bind(this, view)
        return view
    }

    override fun initPresenters() {
        presenter = UserEditPresenter(this)
    }

    override fun onDestroy() {
        butterKnife.unbind()
        super.onDestroy()
    }

    @OnClick(R.id.btn_save)
    fun onSave(view: Button) {
        presenter.updateUserName(
                edtUserName.text.toString()
        )
    }

}