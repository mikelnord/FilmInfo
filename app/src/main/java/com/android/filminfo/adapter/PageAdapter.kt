package com.android.filminfo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.filminfo.ui.FindFragment
import com.android.filminfo.ui.ResultFindFragment
import com.android.filminfo.ui.StartFragment


const val FIND_PAGE_INDEX = 2
const val RESULT_PAGE_INDEX = 1
const val START_PAGE_INDEX = 0

class PageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        FIND_PAGE_INDEX to { FindFragment() },
        RESULT_PAGE_INDEX to { ResultFindFragment() },
        START_PAGE_INDEX to { StartFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}