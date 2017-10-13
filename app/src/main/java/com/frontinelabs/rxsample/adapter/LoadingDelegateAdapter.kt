package com.frontinelabs.rxsample.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.frontinelabs.rxsample.R
import com.frontinelabs.rxsample.`interface`.ViewType
import com.frontinelabs.rxsample.`interface`.ViewTypeDelegateAdapter
import com.frontinelabs.rxsample.inflate

/**
 * Created by EBaba on 12/10/2017.
 */

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item_loading))
}