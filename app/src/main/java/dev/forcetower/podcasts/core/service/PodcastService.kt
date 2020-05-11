package dev.forcetower.podcasts.core.service

import dev.forcetower.podcasts.model.dto.PaginatedResponse
import dev.forcetower.podcasts.model.persistence.Podcast
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastService {
    @GET("nerdcasts")
    suspend fun nerdcasts(@Query("paginated") paginated: Boolean = true): PaginatedResponse<Podcast>
}