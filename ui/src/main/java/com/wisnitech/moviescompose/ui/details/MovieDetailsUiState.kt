package com.wisnitech.moviescompose.ui.details

import com.wisnitech.repository.model.MovieDetails

sealed interface MovieDetailsUiState {
    data object Loading : MovieDetailsUiState
    data class Success(val movieDetails: MovieDetails) : MovieDetailsUiState
}