/*
 * Copyright 2020 http://mobile-dev.pro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobiledevpro.common.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes
    private val layoutId: Int,
    private val settings: FragmentSettings
) : Fragment() {

    lateinit var viewBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(settings.optionsMenuId != 0)
    }

    /**
     * Called to Initialize view data binding variables when fragment view is created.
     */
    abstract fun onInitDataBinding()

    abstract fun observeLifecycleEvents()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitDataBinding()
        observeLifecycleEvents()
        applyResources()
    }

    override fun onStop() {
        val activity: Activity? = requireActivity()

        //hide keyboard if it was shown
        val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

        inputManager?.let {
            val view = activity?.currentFocus
            it.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
        }

        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //Menu items are doubling after fragment has been re-created. Need to execute clear()
        menu.clear()
        if (settings.optionsMenuId != 0) {
            inflater.inflate(settings.optionsMenuId, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun applyResources() {
        (requireActivity() is BaseActivityInterface).apply {
            if (!this) {
                if (settings.statusBarColor != 0)
                    throw UnsupportedOperationException("Your activity should extends from 'BaseActivity' to set StatusBar color")
                if (settings.appBarColor != 0)
                    throw UnsupportedOperationException("Your activity should extends from 'BaseActivity' to set AppBar color")
                if (settings.appBarTitle as Int != 0 || (settings.appBarTitle as String).isEmpty())
                    throw UnsupportedOperationException("Your activity should extends from 'BaseActivity' to set AppBar title")
                if (settings.appBarSubTitle as Int != 0 || (settings.appBarSubTitle as String).isEmpty())
                    throw UnsupportedOperationException("Your activity should extends from 'BaseActivity' to set AppBar sub-title")
                if (settings.homeIconId != 0)
                    throw UnsupportedOperationException("Your activity should extends from 'BaseActivity' to set home indicator icon")
            }

            (requireActivity() as BaseActivityInterface).apply {
                //apply title
                setAppBarTitle(
                    when (settings.appBarTitle) {
                        is Int -> if (settings.appBarTitle != 0) resources.getString(settings.appBarTitle) else ""
                        is String -> if (settings.appBarTitle.isNotEmpty()) settings.appBarTitle else ""
                        else -> ""
                    }
                )

                //apply sub-title
                setAppBarSubTitle(
                    when (settings.appBarSubTitle) {
                        is Int -> if (settings.appBarSubTitle != 0) resources.getString(settings.appBarSubTitle) else ""
                        is String -> if (settings.appBarSubTitle.isNotEmpty()) settings.appBarSubTitle else ""
                        else -> ""
                    }
                )

                //apply color to appbar
                if (settings.appBarColor != 0)
                    setAppBarColor(settings.appBarColor)
                //apply color to status bar
                if (settings.statusBarColor != 0)
                    setStatusBarColor(settings.statusBarColor)
                if (settings.homeIconId != 0)
                //apply home icon
                    setHomeAsUpIndicatorIcon(settings.homeIconId)

            }
        }

    }
}
