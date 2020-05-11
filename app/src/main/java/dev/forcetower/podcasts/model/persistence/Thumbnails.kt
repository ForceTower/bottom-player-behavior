package dev.forcetower.podcasts.model.persistence

import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("img-4x3-355x266")
    val img4x3: String,
    @SerializedName("img-16x9-1210x544")
    val img16x9: String
)