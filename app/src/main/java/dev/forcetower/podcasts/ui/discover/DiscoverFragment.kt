package dev.forcetower.podcasts.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.forcetower.podcasts.core.base.BaseFragment
import dev.forcetower.podcasts.databinding.FragmentDiscoverBinding

class DiscoverFragment : BaseFragment() {
    private lateinit var binding: FragmentDiscoverBinding
    private lateinit var adapter: PodcastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = PodcastAdapter()
        return FragmentDiscoverBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.podcastRecycler.apply {
            adapter = this@DiscoverFragment.adapter
        }
    }
}