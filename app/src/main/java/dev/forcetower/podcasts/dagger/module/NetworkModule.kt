package dev.forcetower.podcasts.dagger.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dev.forcetower.podcasts.BuildConfig
import dev.forcetower.podcasts.Constants
import dev.forcetower.podcasts.core.service.PodcastService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    fun providePodcastService(client: OkHttpClient, gson: Gson): PodcastService {
        return Retrofit.Builder()
            .baseUrl(Constants.API_SERVICE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PodcastService::class.java)
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .followRedirects(true)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BASIC
                else
                    HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }
}