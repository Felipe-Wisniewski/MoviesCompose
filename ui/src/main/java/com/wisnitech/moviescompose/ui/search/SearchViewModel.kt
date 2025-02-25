package com.wisnitech.moviescompose.ui.search

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wisnitech.repository.model.MovieResume
import com.wisnitech.repository.repositories.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val SEARCH_QUERY = "searchQuery"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    searchRepository: SearchRepository
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchPagingItems: StateFlow<PagingData<MovieResume>> = searchQuery.flatMapLatest { query ->
        if (query.trim().length > 1) searchRepository.searchMoviesAndTvShows(query)
        else flowOf(PagingData.empty())
    }.distinctUntilChanged()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )

    fun onSearchQueryChanged(query: String) {
        Log.d("FLMWG", "searchQuery: $query")
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onSearchTriggered(query: String) {
        if (query.isNotBlank()) {
            Log.d("FLMWG", "searchTriggered: not blank $query")
        }
    }
}