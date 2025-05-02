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
import com.wisnitech.repository.model.MediaType

@Composable
fun MoviesHome(
    viewModel: MoviesHomeViewModel = hiltViewModel(),
    onNavigateToDetails: (mediaType: MediaType, mediaId: Int) -> Unit
) {
    val trendingUiState by viewModel.trendingUiState.collectAsStateWithLifecycle()

    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()
    val upcomingMovies = viewModel.upcomingMovies.collectAsLazyPagingItems()

    val popularTvShows = viewModel.popularTvShows.collectAsLazyPagingItems()
    val topRatedTvShows = viewModel.topRatedTvShows.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when (trendingUiState) {
            is TrendingUiState.Loading -> LoadingView()

            is TrendingUiState.Success -> {
                val trending = (trendingUiState as TrendingUiState.Success).trending
                HorizontalPagerTrending(trending) { mediaType, mediaId ->
                    onNavigateToDetails(mediaType, mediaId)
                }
            }

            else -> Unit
        }

        LazyRowMovies("Now playing movies >", nowPlayingMovies) { movieId ->
            onNavigateToDetails(MediaType.MOVIE, movieId)
        }

        LazyRowMovies("Top-rated movies >", topRatedMovies) { movieId ->
            onNavigateToDetails(MediaType.MOVIE, movieId)
        }

        LazyRowMovies("Upcoming movies >", upcomingMovies) { movieId ->
            onNavigateToDetails(MediaType.MOVIE, movieId)
        }

        LazyRowMovies("Popular tv shows >", popularTvShows) { tvId ->
            onNavigateToDetails(MediaType.TV, tvId)
        }

        LazyRowMovies("Top-rated tv shows >", topRatedTvShows) { tvId ->
            onNavigateToDetails(MediaType.TV, tvId)
        }
    }
}

