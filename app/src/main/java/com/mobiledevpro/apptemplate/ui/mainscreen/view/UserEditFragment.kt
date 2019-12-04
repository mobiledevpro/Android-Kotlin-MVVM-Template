package com.mobiledevpro.apptemplate.ui.mainscreen.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobiledevpro.apptemplate.Event
import com.mobiledevpro.apptemplate.R
import com.mobiledevpro.apptemplate.ViewModelFactory
import com.mobiledevpro.apptemplate.databinding.FragmentUserEditBinding
import com.mobiledevpro.apptemplate.ui.mainscreen.viewmodel.UserDataViewModel
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
class UserEditFragment : BaseFragment() {

    private lateinit var userViewModel: UserDataViewModel
    private lateinit var binding: FragmentUserEditBinding

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
        //databinding
        binding = FragmentUserEditBinding.bind(view)
                .apply {
                    userDataModel = userViewModel
                }
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun initPresenters() {
        //init view model instead of presenter
        val app = requireNotNull(activity).application
        val viewModelFactory = ViewModelFactory(app)

        //init ViewModel for this fragment
        userViewModel = ViewModelProvider(activity as FragmentActivity, viewModelFactory)
                .get(UserDataViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //observe toast messages
        setupToastMessages(this,
                userViewModel.showToastEvent,
                Toast.LENGTH_SHORT)
    }

    /**
     * Observe on toast messages
     */
    private fun setupToastMessages(lifecycleOwner: LifecycleOwner,
                                   toastEvent: LiveData<Event<String>>,
                                   timeLength: Int) {

        toastEvent.observe(lifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { txt ->
                Toast.makeText(context, txt, timeLength).show()
            }
        })

    }
}