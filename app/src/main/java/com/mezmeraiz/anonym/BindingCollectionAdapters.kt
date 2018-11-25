package com.mezmeraiz.anonym

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.databinding.BindingAdapter
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.widget.ImageView
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView
import com.mezmeraiz.anonym.ui.views.PaginateRecyclerView
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

object BindingCollectionAdapters {

    @BindingAdapter("decoration")
    @JvmStatic
    fun addItemDecoration(recyclerView: RecyclerView, decoration: RecyclerView.ItemDecoration?){
        recyclerView.addItemDecoration(decoration)
    }

    @BindingAdapter("picasso")
    @JvmStatic
    fun picasso(imageView: ImageView,
                src: String?){
        if (src != null && !src.isEmpty()){
            Picasso.get().load(src).fit().centerInside().into(imageView)
        }
    }

    @BindingAdapter("picasso", "listener")
    @JvmStatic
    fun picassoWithListener(imageView: ImageView,
                src: String?, listener: com.squareup.picasso.Callback){
        if (src != null && !src.isEmpty()){
            Picasso.get().load(src)
                    .fit().centerInside().into(imageView, listener)

        }
    }

    @BindingAdapter("tabWithPager")
    @JvmStatic
    fun setupWithViewPager(tabLayout: TabLayout, viewPager: ViewPager?){
        tabLayout.setupWithViewPager(viewPager)
    }

    @BindingAdapter("adapter")
    @JvmStatic
    fun setupWithViewPager(viewPager: ViewPager, adapter: FragmentPagerAdapter?){
        viewPager.adapter = adapter
    }

    @BindingAdapter("paginateLoadingState")
    @JvmStatic
    fun setPaginateLoadingState(recyclerView: PaginateRecyclerView, state: Boolean){
        recyclerView.isLoading = state
    }

    @BindingAdapter("setRefreshing")
    @JvmStatic
    fun setRefreshing(layout: SwipeRefreshLayout, state: Boolean?){
        if (state != null){
            layout.setRefreshing(state)
        }
    }

    @BindingAdapter("setRefreshListener")
    @JvmStatic
    fun setRefreshListener(layout: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener){
        layout.setOnRefreshListener(listener)
    }

}