package dev.forcetower.podcasts.dagger.module

import android.content.ComponentName
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dev.forcetower.podcasts.PodcastsApp
import dev.forcetower.podcasts.core.music.MusicServiceConnection
import dev.forcetower.podcasts.core.service.converters.TimeConverters
import dev.forcetower.podcasts.service.music.MusicService
import org.threeten.bp.LocalDateTime
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideContext(application: PodcastsApp): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, TimeConverters.ZDT_DESERIALIZER)
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .serializeNulls()
            .create()
    }

    @Provides
    @Singleton
    fun provideMusicConnection(context: Context) =
        MusicServiceConnection(context, ComponentName(context, MusicService::class.java))

//    @Provides
//    @Singleton
//    fun providePodcastService(
//        context: Context,
//        database: BSMDatabase
//    ): MusicSource {
//        return PodcastSource(context, database)
//    }
}