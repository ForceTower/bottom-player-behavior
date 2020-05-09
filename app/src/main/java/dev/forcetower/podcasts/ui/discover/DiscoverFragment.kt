package dev.forcetower.podcasts.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.forcetower.podcasts.core.base.BaseFragment
import dev.forcetower.podcasts.databinding.FragmentDiscoverBinding

class DiscoverFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentDiscoverBinding.inflate(inflater, container, false).root
    }
}