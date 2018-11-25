package com.mezmeraiz.anonym.ui.views

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class PaginateRecyclerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0)
    : RecyclerView(context, attrs, defStyleAttr){

    var onLoadMoreListener: OnLoadMoreListener? = null

    private var previousTotal = 0

    var isLoading = false

    var manager: LinearLayoutManager? = null
        get() {
            if (field == null){
                field = LinearLayoutManager(context)
            }
            return field
        }

    init {
        this.layoutManager = manager
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisiblePosition = (layoutManager as LinearLayoutManager)
                        .findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (isLoading && totalItemCount > previousTotal){
                    previousTotal = totalItemCount
                }
                if ((totalItemCount - 1) == lastVisiblePosition){
                    if (!isLoading) {
                        onLoadMoreListener?.onLoadMore(totalItemCount)
                    }
                }
            }
        })
    }

    interface OnLoadMoreListener {
        fun onLoadMore(offset: Int)
    }

}