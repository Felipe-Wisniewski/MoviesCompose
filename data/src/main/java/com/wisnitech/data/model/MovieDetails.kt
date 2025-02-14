package com.wisnitech.data.model

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
    val video: VideoDetails?,
    val cast: List<Person>?,
    val director: Person?
)