package dev.forcetower.podcasts.core.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

open class BaseFragmentPagerAdapter(
    private val fragments: List<Fragment>,
    fm: FragmentManager,
    behavior: Int = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
}