package dev.forcetower.podcasts.dagger

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.forcetower.podcasts.PodcastsApp
import dev.forcetower.podcasts.dagger.module.ActivityModule
import dev.forcetower.podcasts.dagger.module.AppModule
import dev.forcetower.podcasts.dagger.module.NetworkModule
import dev.forcetower.podcasts.dagger.module.ViewModelModule

@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
//    DatabaseModule::class,
    ViewModelModule::class,
    ActivityModule::class
])
interface AppComponent : AndroidInjector<PodcastsApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PodcastsApp): Builder
        fun build(): AppComponent
    }
}