package com.android.filminfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.android.filminfo.R
import com.android.filminfo.adapter.FIND_PAGE_INDEX
import com.android.filminfo.adapter.PageAdapter
import com.android.filminfo.adapter.RESULT_PAGE_INDEX
import com.android.filminfo.adapter.START_PAGE_INDEX
import com.android.filminfo.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        viewPager = binding.viewPager

        viewPager.adapter = PageAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    fun setResult() {
        viewPager.currentItem = 1
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            FIND_PAGE_INDEX -> R.drawable.ic_find_selector
            RESULT_PAGE_INDEX -> R.drawable.result_list_selector
            START_PAGE_INDEX -> R.drawable.ic_start_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            FIND_PAGE_INDEX -> getString(R.string.find_title)
            RESULT_PAGE_INDEX -> getString(R.string.result_title)
            START_PAGE_INDEX -> getString(R.string.start_title)
            else -> null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}