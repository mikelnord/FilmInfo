package com.android.filminfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.android.filminfo.R
import com.android.filminfo.adapter.DESC_PAGE_INDEX
import com.android.filminfo.adapter.PERSON_PAGE_INDEX
import com.android.filminfo.adapter.PageDetailAdapter
import com.android.filminfo.databinding.FragmentDetailHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailFragment : Fragment() {

    private var _binding: FragmentDetailHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailHomeBinding.inflate(inflater, container, false)


        val tabLayout = binding.tabs
        viewPager = binding.viewPager

        viewPager.adapter = PageDetailAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            PERSON_PAGE_INDEX -> getString(R.string.person_title)
            DESC_PAGE_INDEX -> getString(R.string.desc_title)
            else -> null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}