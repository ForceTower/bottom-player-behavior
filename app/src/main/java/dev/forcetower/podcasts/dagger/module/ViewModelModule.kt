package dev.forcetower.podcasts.dagger.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.forcetower.podcasts.core.base.BaseViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory
}