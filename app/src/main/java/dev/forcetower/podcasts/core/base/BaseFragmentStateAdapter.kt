package dev.forcetower.podcasts.core.base

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

// This is a ViewPager2 thingy... The old one might be deleted soon
open class BaseFragmentStateAdapter(
    private val fragments: List<Fragment>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int) = fragments[position]
    override fun getItemCount() = fragments.size
}