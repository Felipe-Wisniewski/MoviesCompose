package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.MovieDetails

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
    val genres: List<NetworkGenres>?
)

fun ResponseMovieDetails.asExternalModel() = MovieDetails(
    id = id,
    title = title,
    overview = overview,
    tagline = tagline,
    status = status,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    originalLanguage = originalLanguage,
    homepage = homepage,
    budget = budget,
    revenue = revenue,
    popularity = popularity,
    voteAverage = voteAverage,
    voteCount = voteCount,
    genres = genres?.map { it.name }
)

// other calls
//
// recommendation list (movieId)
//
// credits.cast > acting
// credits.crew > "department": "Directing", "job": "Director"