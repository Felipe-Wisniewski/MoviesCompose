package com.wisnitech.moviescompose.ui.components

import com.wisnitech.repository.model.MediaType
import com.wisnitech.repository.model.MovieResume

val listMovieResume = listOf(
    MovieResume(
        id = 1,
        title = "Mufasa: The Lion King",
        mediaType = MediaType.MOVIE,
        overview = "Mufasa, a cub lost and alone, meets a sympathetic lion named Taka, the heir to a royal bloodline. The chance meeting sets in motion an expansive journey of a group of misfits searching for their destiny.",
        backdropUrl = "",
        posterUrl = "",
        adult = false,
        language = "en",
        genreIds = listOf(12, 10751, 16),
        popularity = 3017.42f,
        premiereDay = "2024-12-18",
        voteAverage = 7.5f,
        voteCount = 1152
    ),
    MovieResume(
        id = 2,
        title = "The King of The Cattle",
        mediaType = MediaType.TV,
        overview = "In the struggle for land a vicious feud erupts between two men, who commit their families to a war without truce. Over two generations they are moved by love and by hatred in this epic saga of love for the land.",
        backdropUrl = "",
        posterUrl = "",
        adult = false,
        language = "pt",
        genreIds = listOf(10766, 18, 10751),
        popularity = 44.043f,
        premiereDay = "1996-06-17",
        voteAverage = 7.674f,
        voteCount = 23
    ),
    MovieResume(
        id = 3,
        title = "Ohsama Sentai King-Ohger",
        mediaType = MediaType.TV,
        overview = "There is an ancient prophecy shared amongst the kingdoms of Chikyu- 2,000 years after their fall, the Territorial Empire Bugnarok will once again rise up to kill all humans. However, five kings and their guardian deity, King-Ohger, will stand up to face them. This is the story of kings who will defend Chikyu, as well as a coming-of-age story for one young man who will achieve kingship henceforth.",
        backdropUrl = "",
        posterUrl = "",
        adult = false,
        language = "ja",
        genreIds = listOf(10759, 10765, 10762),
        popularity = 82.997f,
        premiereDay = "2023-03-05",
        voteAverage = 7.6f,
        voteCount = 116
    )
)