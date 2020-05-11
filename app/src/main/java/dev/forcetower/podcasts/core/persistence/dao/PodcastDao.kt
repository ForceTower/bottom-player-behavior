package dev.forcetower.podcasts.core.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import dev.forcetower.podcasts.model.persistence.Podcast

@Dao
abstract class PodcastDao : BaseDao<Podcast>() {
    @Query("SELECT * FROM Podcast WHERE id = :id")
    abstract suspend fun getPodcast(id: Int): Podcast?

    override suspend fun getValueByIDDirect(value: Podcast): Podcast? {
        return getPodcast(value.id)
    }
}