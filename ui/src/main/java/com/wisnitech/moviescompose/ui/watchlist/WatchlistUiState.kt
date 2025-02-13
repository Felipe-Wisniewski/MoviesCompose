package com.wisnitech.moviescompose.ui.watchlist

import com.wisnitech.data.model.Movie

sealed interface WatchlistUiState {
    data object Loading : WatchlistUiState
    data class Watchlist(val listMovies: List<Movie>) : WatchlistUiState
}