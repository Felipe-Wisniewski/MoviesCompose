package com.wisnitech.moviescompose.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wisnitech.data.model.Movie
import com.wisnitech.data.model.Trending
import com.wisnitech.data.repositories.movies.MoviesRepository
import com.wisnitech.data.repositories.trending.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    trendingRepository: TrendingRepository,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    val trendingUiState: StateFlow<TrendingUiState> = trendingRepository.loadAllTrending()
        .map(TrendingUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TrendingUiState.Loading
        )

    private val _topRatedMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val topRatedMovies: StateFlow<PagingData<Movie>> = _topRatedMovies

    private val _popularMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val popularMovies: StateFlow<PagingData<Movie>> = _popularMovies

    private val _upcomingMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val upcomingMovies: StateFlow<PagingData<Movie>> = _upcomingMovies

    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private fun getTopRatedMovies() = viewModelScope.launch {
        moviesRepository.getTopRatedMovies()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _topRatedMovies.value = it
            }
    }

    private fun getPopularMovies() = viewModelScope.launch {
        moviesRepository.getPopularMovies()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _popularMovies.value = it
            }
    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        moviesRepository.getUpcomingMovies()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _upcomingMovies.value = it
            }
    }
}