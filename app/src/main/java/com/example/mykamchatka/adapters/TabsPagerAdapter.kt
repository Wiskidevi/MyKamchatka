package com.example.mykamchatka.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mykamchatka.news_branch.AllNewsTabFragment
import com.example.mykamchatka.news_branch.EventNewsTabFragment
import com.example.mykamchatka.news_branch.FactNewsTabFragment
import com.example.mykamchatka.news_branch.HistoryNewsTabFragment

class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // # All Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "All Fragment")
                val allNewsFragment = AllNewsTabFragment.newInstance()
                allNewsFragment.arguments = bundle
                return allNewsFragment
            }
            1 -> {
                // # Events Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Event Fragment")
                val eventNewsFragment = EventNewsTabFragment.newInstance()
                eventNewsFragment.arguments = bundle
                return eventNewsFragment
            }
            2 -> {
                // # History Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Books Fragment")
                val historyNewsFragment = HistoryNewsTabFragment.newInstance()
                historyNewsFragment.arguments = bundle
                return historyNewsFragment
            }
            3 -> {
                // # Facts Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Books Fragment")
                val factNewsFragment = FactNewsTabFragment.newInstance()
                factNewsFragment.arguments = bundle
                return factNewsFragment
            }
            else -> return AllNewsTabFragment.newInstance()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}