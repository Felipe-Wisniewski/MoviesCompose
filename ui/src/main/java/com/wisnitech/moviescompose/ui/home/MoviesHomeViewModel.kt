package com.wisnitech.moviescompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wisnitech.data.model.Movie
import com.wisnitech.data.repositories.movies.MoviesRepository
import com.wisnitech.data.repositories.trending.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    trendingRepository: TrendingRepository,
    moviesRepository: MoviesRepository
) : ViewModel() {

    val trendingUiState: StateFlow<TrendingUiState> = trendingRepository.loadAllTrending()
        .catch {
            it.printStackTrace()
            TrendingUiState.Error(it.message)
        }
        .map(TrendingUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TrendingUiState.Loading
        )

    val topRatedMovies: StateFlow<PagingData<Movie>> = moviesRepository.getTopRatedMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )

    val popularMovies: StateFlow<PagingData<Movie>> = moviesRepository.getPopularMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )

    val upcomingMovies: StateFlow<PagingData<Movie>> = moviesRepository.getUpcomingMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )
}