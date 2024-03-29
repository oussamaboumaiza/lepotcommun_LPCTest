package com.lakooz.lpctest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

//TODO : Set up the three tabs
class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PotsFragment(0)
            1 -> PotsFragment(1)
            else -> PotsFragment(2)
        }
    }


    override fun getItemCount() = 3
}