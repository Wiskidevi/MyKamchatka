package com.example.mykamchatka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.mykamchatka.databinding.FragmentNewsNavMenuBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class NavNewsFragment : Fragment() {

    private val fragList = listOf(
        AllNewsTabFragment.newInstance(),
        EventNewsTabFragment.newInstance(),
        FactNewsTabFragment.newInstance(),
        HistoryNewsTabFragment.newInstance()
    )

    private var _binding: FragmentNewsNavMenuBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentProfileBinding must not be null")

    private var currentTabPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsNavMenuBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numberOfTabs = 4
        binding.tabLayoutNews.tabMode = TabLayout.MODE_FIXED
        binding.tabLayoutNews.isInlineLabel = true

        val adapter = TabsPagerAdapter(childFragmentManager, lifecycle, numberOfTabs)
        binding.viewPagerNews.adapter = adapter
        binding.viewPagerNews.isUserInputEnabled = true
        binding.viewPagerNews.isSaveEnabled = false

        // Восстановить выбранную вкладку при возврате к фрагменту
        binding.viewPagerNews.setCurrentItem(currentTabPosition, false)

        TabLayoutMediator(binding.tabLayoutNews, binding.viewPagerNews) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Все"
                }
                1 -> {
                    tab.text = "События"
                }
                2 -> {
                    tab.text = "История"
                }
                3 -> {
                    tab.text = "Факты"
                }
            }
        }.attach()

        binding.viewPagerNews.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentTabPosition = position
            }
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = NavNewsFragment()
    }
}