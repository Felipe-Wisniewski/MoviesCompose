package com.wisnitech.repository.model

import com.wisnitech.data.remote.model.ResponseMovieDetails
import com.wisnitech.repository.utils.FIND_CREW_JOB
import com.wisnitech.repository.utils.FIND_VIDEO_TYPE
import com.wisnitech.repository.utils.IMAGE_URL

data class MovieDetails(
    val id: Int,
    val title: String?,
    val overview: String?,
    val tagline: String?,
    val status: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val releaseDate: String?,
    val runtime: Int?,
    val originalLanguage: String?,
    val homepage: String?,
    val budget: Int?,
    val revenue: Int?,
    val popularity: Float?,
    val voteAverage: Double,
    val voteCount: Int?,
    val genres: List<String>?,
    val trailer: Trailer?,
    val cast: List<Person>?,
    val director: Person?,
    var isWatchlist: Boolean = false
)

fun ResponseMovieDetails.asExternalModel() = MovieDetails(
    id = id,
    title = title,
    overview = overview,
    tagline = tagline,
    status = status,
    posterUrl = if (posterPath.isNullOrBlank()) null else IMAGE_URL + posterPath,
    backdropUrl = if (backdropPath.isNullOrBlank()) null else IMAGE_URL + backdropPath,
    releaseDate = releaseDate,
    runtime = runtime,
    originalLanguage = originalLanguage,
    homepage = homepage,
    budget = budget,
    revenue = revenue,
    popularity = popularity,
    voteAverage = voteAverage?.let { Math.round(it * 10.0) / 10.0 } ?: 0.0,
    voteCount = voteCount,
    genres = genres?.map { it.name },
    trailer = videos?.results?.find { it.type == FIND_VIDEO_TYPE && it.official }
        ?.asExternalResumeModel(),
    cast = credits?.cast?.sortedBy { it.order }?.take(6)?.map { it.asExternalResumeModel() },
    director = credits?.crew?.find { it.job == FIND_CREW_JOB }?.asExternalResumeModel()
)