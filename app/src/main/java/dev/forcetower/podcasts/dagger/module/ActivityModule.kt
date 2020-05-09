package dev.forcetower.podcasts.dagger.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.forcetower.podcasts.dagger.module.home.HomeModule
import dev.forcetower.podcasts.ui.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun main(): MainActivity
}