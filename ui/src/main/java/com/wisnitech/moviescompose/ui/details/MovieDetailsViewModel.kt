package com.wisnitech.moviescompose.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.wisnitech.moviescompose.ui.home.MovieDetailsRoute
import com.wisnitech.repository.model.MediaType
import com.wisnitech.repository.repositories.account.AccountRepository
import com.wisnitech.repository.repositories.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    moviesRepository: MovieRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val route = savedStateHandle.toRoute<MovieDetailsRoute>()

    private var movieId = -1

    val detailsUiState: StateFlow<MovieDetailsUiState> =
        moviesRepository.loadMovieDetails(route.movieId)
            .catch { it.printStackTrace() }
            .map {
                movieId = it.id
                MovieDetailsUiState.Success(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MovieDetailsUiState.Loading
            )

    private val _feedbackActions = MutableStateFlow<DetailsActions?>(null)
    val feedbackActions: StateFlow<DetailsActions?> = _feedbackActions

    fun saveOrRemoveToWatchlist(isWatchlist: Boolean) {
        viewModelScope.launch {
            accountRepository.saveOrRemoveToWatchlist(isWatchlist, MediaType.MOVIE.value, movieId)
                .catch {
                    it.printStackTrace()
                    _feedbackActions.value = DetailsActions.ERROR_WATCHLIST
                }
                .collect {
                    _feedbackActions.value = if (it) DetailsActions.ADD_WATCHLIST
                    else DetailsActions.REMOVE_WATCHLIST
                }
        }
    }

    fun setLikeMovie() {

    }

    fun setUnlikeMovie() {

    }
}