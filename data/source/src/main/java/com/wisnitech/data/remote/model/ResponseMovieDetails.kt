package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.MovieDetails
import com.wisnitech.data.remote.utils.FIND_CREW_JOB
import com.wisnitech.data.remote.utils.FIND_VIDEO_TYPE
import com.wisnitech.data.remote.utils.IMAGE_URL

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
    trailer = videos?.results?.find { it.type == FIND_VIDEO_TYPE && it.official }?.asExternalResumeModel(),
    cast = credits?.cast?.sortedBy { it.order }?.take(6)?.map { it.asExternalResumeModel() },
    director = credits?.crew?.find { it.job == FIND_CREW_JOB }?.asExternalResumeModel()
)