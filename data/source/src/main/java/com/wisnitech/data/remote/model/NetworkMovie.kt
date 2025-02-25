package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName

data class NetworkMovie(
    val id: Int,
    val name: String?,
    val title: String?,
    @SerializedName("media_type")
    val mediaType: String?,
    val overview: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val adult: Boolean,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    val popularity: Float,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?
)