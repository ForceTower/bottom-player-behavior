package dev.forcetower.podcasts.ui.discover

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.forcetower.podcasts.R
import dev.forcetower.podcasts.core.extensions.inflate
import dev.forcetower.podcasts.databinding.ItemPodcastBinding
import dev.forcetower.podcasts.model.persistence.Podcast

class PodcastAdapter : PagedListAdapter<Podcast, PodcastAdapter.PodcastHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PodcastHolder {
        return PodcastHolder(parent.inflate(R.layout.item_podcast))
    }

    override fun onBindViewHolder(holder: PodcastHolder, position: Int) {
        holder.binding.podcast = getItem(position)
    }

    inner class PodcastHolder(val binding: ItemPodcastBinding) : RecyclerView.ViewHolder(binding.root)

    private object DiffCallback : DiffUtil.ItemCallback<Podcast>() {
        override fun areItemsTheSame(oldItem: Podcast, newItem: Podcast) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Podcast, newItem: Podcast) = oldItem == newItem
    }
}
