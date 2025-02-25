package com.wisnitech.repository.model

import com.wisnitech.data.remote.model.NetworkMovie
import com.wisnitech.repository.utils.IMAGE_URL

data class MovieResume(
    val id: Int,
    val title: String,
    val mediaType: MediaType?,
    val overview: String,
    val backdropUrl: String?,
    val posterUrl: String?,
    val adult: Boolean,
    val language: String?,
    val genreIds: List<Int>?,
    val popularity: Float,
    val premiereDay: String?,
    val voteAverage: Float?,
    val voteCount: Int?
)

fun NetworkMovie.asExternalResumeModel() = MovieResume(
    id = id,
    title = when (MediaType.entries.firstOrNull { it.name == mediaType?.uppercase() }) {
        MediaType.TV -> name ?: "-"
        MediaType.MOVIE -> title ?: "-"
        MediaType.PERSON -> name ?: "-"
        else -> "-"
    },
    mediaType = MediaType.entries.firstOrNull { it.name == mediaType?.uppercase() },
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