package com.wisnitech.moviescompose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.wisnitech.moviescompose.ui.components.LoadingView
import com.wisnitech.moviescompose.ui.components.HorizontalPagerTrending
import com.wisnitech.moviescompose.ui.components.LazyRowMovies

@Composable
fun MoviesHome(
    viewModel: MoviesHomeViewModel = hiltViewModel(),
    onNavigateToDetails: (movieId: Int) -> Unit
) {
    val trendingUiState by viewModel.trendingUiState.collectAsStateWithLifecycle()
    val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val upcomingMovies = viewModel.upcomingMovies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when (trendingUiState) {
            is TrendingUiState.Loading -> LoadingView()

            is TrendingUiState.Success -> {
                val trending = (trendingUiState as TrendingUiState.Success).trending
                HorizontalPagerTrending(trending)
            }

            else -> Unit
        }

        LazyRowMovies("Top Rated Movies", topRatedMovies) { movieId ->
            onNavigateToDetails(movieId)
        }

        LazyRowMovies("Popular Movies", popularMovies) { movieId ->
            onNavigateToDetails(movieId)
        }

        LazyRowMovies("Upcoming Movies", upcomingMovies) { movieId ->
            onNavigateToDetails(movieId)
        }
    }
}

