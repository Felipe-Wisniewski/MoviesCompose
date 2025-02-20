package com.wisnitech.moviescompose.ui.watchlist

import com.wisnitech.data.model.MovieResume

sealed interface WatchlistUiState {
    data object Loading : WatchlistUiState
    data class Watchlist(val listMovies: List<MovieResume>) : WatchlistUiState
}