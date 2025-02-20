package com.wisnitech.data.model

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
