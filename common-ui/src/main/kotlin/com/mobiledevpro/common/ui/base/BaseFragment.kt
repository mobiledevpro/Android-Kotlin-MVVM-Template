/*
 * Copyright 2019 http://mobile-dev.pro
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

import android.os.Bundle
import android.view.*
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes
    private val layoutId: Int,
    @StringRes
    private val appBarTitleId: Int = 0,
    @StringRes
    private val appBarSubTitleId: Int = 0,
    @DrawableRes
    private val homeIconId: Int = 0,
    @MenuRes
    private val optionsMenuId: Int = 0
) : Fragment() {

    lateinit var viewBinding: B
    //lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(optionsMenuId != 0)
    }

    /**
     * Called to Initialize view data binding variables when fragment view is created.
     */
    abstract fun onInitDataBinding()

    abstract fun observeLifecycleEvents()

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be
     * attached to. The fragment should not add the view itself, but this can be used to generate
     * the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     * saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     *
     * @see Fragment.onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }


    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see Fragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitDataBinding()
        observeLifecycleEvents()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //Menu items are doubling after fragment has been re-created. Need to call clear()
        menu.clear()
        if (optionsMenuId != 0) {
            inflater.inflate(optionsMenuId, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

}
