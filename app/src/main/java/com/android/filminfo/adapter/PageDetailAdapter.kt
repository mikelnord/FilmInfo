package com.android.filminfo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.filminfo.ui.*


const val PERSON_PAGE_INDEX = 1
const val DESC_PAGE_INDEX = 0

class PageDetailAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        DESC_PAGE_INDEX to { MovieDetailFragment() },
        PERSON_PAGE_INDEX to { PersonFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}