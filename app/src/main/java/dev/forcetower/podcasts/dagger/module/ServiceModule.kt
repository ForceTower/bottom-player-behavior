package dev.forcetower.podcasts.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.forcetower.podcasts.service.music.MusicService

@Module
abstract class ServiceModule {
    @ContributesAndroidInjector
    abstract fun music(): MusicService
}