package com.wisnitech.moviescompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wisnitech.repository.model.Movie
import com.wisnitech.repository.repositories.movie.MovieRepository
import com.wisnitech.repository.repositories.trending.TrendingRepository
import com.wisnitech.repository.repositories.tv.TvRepository
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
    moviesRepository: MovieRepository,
    tvRepository: TvRepository
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

    val nowPlayingMovies: StateFlow<PagingData<Movie>> = moviesRepository.loadNowPlayingMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )

    val topRatedMovies: StateFlow<PagingData<Movie>> = moviesRepository.loadTopRatedMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )

    val upcomingMovies: StateFlow<PagingData<Movie>> = moviesRepository.loadUpcomingMovies()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )

    val popularTvShows: StateFlow<PagingData<Movie>> = tvRepository.loadPopularTvShows()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )

    val topRatedTvShows: StateFlow<PagingData<Movie>> = tvRepository.loadTopRatedTvShows()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )
}