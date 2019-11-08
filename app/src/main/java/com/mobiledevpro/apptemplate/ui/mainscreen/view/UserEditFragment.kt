package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.IUserEdit
import com.mobiledevpro.apptemplate.ui.mainscreen.presenter.UserEditPresenter
import com.mobiledevpro.apptemplate.uimodel.UserDataViewModel
import com.mobiledevpro.commons.fragment.BaseFragment
import com.mobiledevpro.data.LOG_TAG_DEBUG

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

    private lateinit var viewModel: UserDataViewModel

    companion object {
        fun newInstance(): UserEditFragment {

            val args = Bundle()

            val fragment = UserEditFragment()
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

    override fun getLayoutResId(): Int = R.layout.fragment_user_edit

    override fun populateView(view: View, bundle: Bundle?): View {
        butterKnife = ButterKnife.bind(this, view)
        Log.d(LOG_TAG_DEBUG, "UserEditFragment.populateView")

        //  edtUserName.setText(viewModel.getUserName())
        return view
    }

    override fun initPresenters() {
        presenter = UserEditPresenter(this)
        lifecycle.addObserver(presenter)
    }

    override fun onDestroy() {
        butterKnife.unbind()
        super.onDestroy()
    }

    override fun showShortMessage(txt: String) {
        Toast.makeText(activity, txt, Toast.LENGTH_SHORT).show()
    }

    @OnClick(R.id.btn_save)
    fun onSave(view: Button) {
        /* presenter.updateUserName(
                 edtUserName.text.toString()
         )*/
        viewModel.setUserName(edtUserName.text.toString())
    }

}