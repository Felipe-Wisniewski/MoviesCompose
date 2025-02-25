package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.MediaType
import com.wisnitech.data.model.Movie
import com.wisnitech.data.model.MovieResume
import com.wisnitech.data.remote.utils.IMAGE_URL

data class NetworkMovie(
    val id: Int,
    val name: String?,
    val title: String?,
    @SerializedName("media_type")
    val mediaType: MediaType?,
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

fun NetworkMovie.asExternalModel() = Movie(
    id = id,
    title = title ?: "-",
    posterUrl = if (posterPath.isNullOrBlank()) null else IMAGE_URL + posterPath,
    backdropUrl = if (backdropPath.isNullOrBlank()) null else IMAGE_URL + backdropPath
)

fun NetworkMovie.asExternalResumeModel() = MovieResume(
    id = id,
    title = when (mediaType) {
        MediaType.TV -> name ?: "-"
        MediaType.MOVIE -> title ?: "-"
        else -> "-"
    },
    mediaType = mediaType,
    overview = overview ?: "-",
    backdropUrl = IMAGE_URL + backdropPath,
    posterUrl = if (posterPath.isNullOrBlank()) null else IMAGE_URL + posterPath,
    adult = adult,
    language = originalLanguage,
    genreIds = genreIds,
    popularity = popularity,
    premiereDay = firstAirDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun NetworkMovie.asExternalResumeModel(type: MediaType) = MovieResume(
    id = id,
    title = when (type) {
        MediaType.TV -> name ?: "-"
        MediaType.MOVIE -> title ?: "-"
        else -> "-"
    },
    mediaType = type,
    overview = overview ?: "-",
    backdropUrl = IMAGE_URL + backdropPath,
    posterUrl = if (posterPath.isNullOrBlank()) null else IMAGE_URL + posterPath,
    adult = adult,
    language = originalLanguage,
    genreIds = genreIds,
    popularity = popularity,
    premiereDay = firstAirDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)