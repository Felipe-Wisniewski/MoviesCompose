package com.wisnitech.moviescompose.ui.watchlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.wisnitech.moviescompose.ui.components.ListVideosWithResume
import com.wisnitech.moviescompose.ui.components.LoadingView
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun WatchlistScreen(viewModel: WatchlistViewModel = hiltViewModel()) {
    val uiState by viewModel.watchlistUiState.collectAsStateWithLifecycle()

    when (uiState) {
        WatchlistUiState.Loading -> LoadingView()
        is WatchlistUiState.Watchlist -> {
            val movies = (uiState as WatchlistUiState.Watchlist).listMovies
            val pagingData = PagingData.from(movies)
            val dataFlow = MutableStateFlow(pagingData)
            ListVideosWithResume(dataFlow.collectAsLazyPagingItems())
        }
    }
}

