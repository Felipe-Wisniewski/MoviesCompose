package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseMovieDetails(
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
    val runtime:Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    val homepage: String?,
    val budget: Int?,
    val revenue: Int?,
    val popularity: Float?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    val genres: List<NetworkGenres>?,
    val videos: ResponseVideos?,
    val credits: NetworkCredits?
)