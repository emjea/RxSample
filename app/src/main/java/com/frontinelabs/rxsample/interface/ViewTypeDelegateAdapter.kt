package com.frontinelabs.rxsample.`interface`

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by EBaba on 12/10/2017.
 */

interface ViewTypeDelegateAdapter{
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}