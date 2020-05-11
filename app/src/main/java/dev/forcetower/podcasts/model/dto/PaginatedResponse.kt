package dev.forcetower.podcasts.model.dto

data class PaginatedResponse<T>(
    val data: List<T>,
    val perPage: Int,
    val page: Int,
    val count: Int,
    val pages: Int
)