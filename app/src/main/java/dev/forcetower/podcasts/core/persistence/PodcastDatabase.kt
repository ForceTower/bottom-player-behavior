package dev.forcetower.podcasts.core.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.forcetower.podcasts.core.persistence.dao.PodcastDao
import dev.forcetower.podcasts.model.persistence.Podcast

@Database(entities = [
    Podcast::class
], version = 1)
@TypeConverters(value = [Converter::class])
abstract class PodcastDatabase : RoomDatabase() {
    abstract fun podcast(): PodcastDao
}