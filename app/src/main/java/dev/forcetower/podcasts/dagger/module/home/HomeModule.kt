package dev.forcetower.podcasts.dagger.module.home

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.forcetower.podcasts.ui.discover.DiscoverFragment
import dev.forcetower.podcasts.ui.miniplayer.MiniPlayerFragment

@Module
abstract class HomeModule {
    @ContributesAndroidInjector
    abstract fun discover(): DiscoverFragment
    @ContributesAndroidInjector
    abstract fun miniPlayer(): MiniPlayerFragment
}