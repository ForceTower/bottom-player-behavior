package dev.forcetower.podcasts.model.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

@Entity
data class Podcast(
    @PrimaryKey
    val id: Int,
    val publishedAt: ZonedDateTime,
    val duration: Int,
    val title: String,
    val episode: String,
    val product: String,
    val productName: String,
    val friendlyPostTime: String,
    val friendlyPostDate: String,
    val subject: String,
    val image: String,
    val thumbnails: Thumbnails,
    val audioHigh: String,
    val description: String,
    val guests: String,
    val postTypeClass: String
)