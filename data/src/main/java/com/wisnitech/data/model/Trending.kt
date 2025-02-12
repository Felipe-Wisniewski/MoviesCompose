package com.wisnitech.data.model

import com.google.gson.annotations.SerializedName

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w1280"

data class Trending(
    val id: Int,
    @SerializedName("name")
    val tvShowName: String?,
    @SerializedName("title")
    val movieTitle: String?,
    @SerializedName("media_type")
    val mediaType: MediaType,
    @SerializedName("backdrop_path")
    val backdropPath: String?
)  {

    fun getName() = when (mediaType) {
        MediaType.TV -> tvShowName ?: ""
        MediaType.MOVIE -> movieTitle ?: ""
    }

    fun getBackdropUrl(): String? =
        if (backdropPath.isNullOrBlank()) null else IMAGE_URL + backdropPath

    enum class MediaType(value: String) {
        @SerializedName("tv")
        TV("Tv"),

        @SerializedName("movie")
        MOVIE("Movie")
    }

}
