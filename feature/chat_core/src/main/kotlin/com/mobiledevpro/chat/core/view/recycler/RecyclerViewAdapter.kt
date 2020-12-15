/*
 * Copyright 2020 | Dmitri Chernysh | http://mobile-dev.pro
 *
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
 *
 */
package com.mobiledevpro.chat.core.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Common adapter for Recycler Views
 *
 * Created on Dec 15, 2020.
 *
 */
class RecyclerViewAdapter : RecyclerView.Adapter<BindingViewHolder>() {

    private var items = ArrayList<RecyclerItem>()
    private var eventHandler: RecyclerViewHandler? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder,
                                  position: Int) {
        getItem(position).bind(holder.binding, eventHandler)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutId
    }

    fun setEventHandler(handler: RecyclerViewHandler?) {
        eventHandler = handler
    }

    fun updateData(newItems: List<RecyclerItem>) {
        //TODO: write a correct updating without clearing a whole list

        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }


    private fun getItem(position: Int): RecyclerItem {
        return items[position]
    }

}

class BindingViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root)