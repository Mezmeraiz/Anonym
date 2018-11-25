package com.mezmeraiz.anonym.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter (val fm: FragmentManager, val fragmentList: MutableList<Fragment>,
                        var pageTitles:MutableList<String>):
        FragmentPagerAdapter(fm)  {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageTitles[position]
    }

}