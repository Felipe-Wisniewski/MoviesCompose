package com.wisnitech.moviescompose.ui.watchlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.wisnitech.data.model.Movie
import com.wisnitech.moviescompose.ui.common.LoadingView

@Composable
fun WatchlistScreen(viewModel: WatchlistViewModel = hiltViewModel()) {

    val uiState by viewModel.watchlistUiState.collectAsStateWithLifecycle()

    when (uiState) {
        WatchlistUiState.Loading -> LoadingView()
        is WatchlistUiState.Watchlist -> {
            WatchlistScreen((uiState as WatchlistUiState.Watchlist).listMovies)
        }
    }
}

@Composable
fun WatchlistScreen(movies: List<Movie>) {

    Text(text = "${movies.size} movies")

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            ItemWatchlist(movie)
        }
    }

}

@Composable
fun ItemWatchlist(movie: Movie) {

    Row(
        modifier = Modifier.height(80.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Column {
            AsyncImage(
                modifier = Modifier.clickable { /*movieId(movie.id)*/ },
                model = movie.getBackdropUrl(),
                contentDescription = "backdrop of movie ${movie.title}",
            )
        }

        Column {
            Text(text = movie.title)
            Text(text = "2025")
        }

    }

}