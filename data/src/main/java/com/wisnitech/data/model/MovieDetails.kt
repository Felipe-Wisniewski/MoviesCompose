package com.wisnitech.data.model

import com.google.gson.annotations.SerializedName

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w1280"

data class MovieDetails(
    val id: Int,
    val title: String?,
    val overview: String?,
    val tagline: String?,
    val status: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    val homepage: String?,
    val budget: Int,
    val revenue: Int?,
    val popularity: Float?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    val genres: List<String>?
) {

    fun getBackdropUrl(): String? =
        if (backdropPath.isNullOrBlank()) null else IMAGE_URL + backdropPath

}
