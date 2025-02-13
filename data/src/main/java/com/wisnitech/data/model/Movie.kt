package com.wisnitech.data.model

import com.google.gson.annotations.SerializedName

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w1280"

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?
) {

    fun getPosterUrl(): String? = if (posterPath.isNullOrBlank()) null else IMAGE_URL + posterPath

    fun getBackdropUrl(): String? =
        if (backdropPath.isNullOrBlank()) null else IMAGE_URL + backdropPath

}
