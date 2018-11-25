package com.mezmeraiz.anonym.viewmodel

import android.content.Context
import android.databinding.Bindable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.mezmeraiz.anonym.BR
import com.mezmeraiz.anonym.adapters.ViewPagerAdapter
import com.mezmeraiz.anonym.common.RootViewModel
import com.mezmeraiz.anonym.ui.fragments.PostFragment
import java.util.ArrayList

class MainViewModel: RootViewModel(){

    private var fragmentManager: FragmentManager? = null

    private val pageTitles: MutableList<String> = mutableListOf("НОВОЕ", "ПОПУЛЯРНОЕ")

    @get:Bindable
    var viewPagerAdapter: ViewPagerAdapter? = null
        get() {
            if (field == null && fragmentManager != null){
                val adapter = ViewPagerAdapter(fragmentManager!!, fragmentList!!, pageTitles)
                field = adapter
            }
            return field
        }

    private var fragmentList: MutableList<Fragment>? = null
        get() {
            if (field == null){
                val fragmentList = mutableListOf<Fragment>()
                fragmentList.add(PostFragment.getInstance(1))
                fragmentList.add(PostFragment.getInstance(2))
                field = fragmentList
            }
            return field
        }

    override fun onCreate(context: Context) {
        super.onCreate(context)
        fragmentManager = (context as AppCompatActivity).supportFragmentManager
        notifyPropertyChanged(BR.viewPagerAdapter)
    }

}