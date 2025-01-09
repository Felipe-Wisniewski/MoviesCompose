package com.wisnitech.moviescompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wisnitech.data.models.Movie
import com.wisnitech.data.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _popularMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val popularMovies: StateFlow<PagingData<Movie>> get() = _popularMovies

    private val _topRatedMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val topRatedMovies: StateFlow<PagingData<Movie>> get() = _topRatedMovies

    init {
        getPopularMovies()
        getTopRatedMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        repository.getPopularMovies()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _popularMovies.value = it
            }
    }

    private fun getTopRatedMovies() = viewModelScope.launch {
        repository.getTopRatedMovies()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _topRatedMovies.value = it
            }
    }
}