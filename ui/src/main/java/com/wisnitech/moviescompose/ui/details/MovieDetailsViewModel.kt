package com.wisnitech.moviescompose.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.wisnitech.data.repositories.movie.MovieRepository
import com.wisnitech.moviescompose.ui.home.MovieDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    moviesRepository: MovieRepository
) : ViewModel() {

    private val route = savedStateHandle.toRoute<MovieDetailsRoute>()

    val detailsUiState: StateFlow<MovieDetailsUiState> =
        moviesRepository.loadMovieDetails(route.movieId)
            .catch { it.printStackTrace() }
            .map(MovieDetailsUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MovieDetailsUiState.Loading
            )

    fun saveOrRemoveToWatchlist() {

    }

    fun setLikeMovie() {

    }

    fun setUnlikeMovie() {

    }
}