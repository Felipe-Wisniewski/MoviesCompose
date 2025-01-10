package com.wisnitech.moviescompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.wisnitech.data.models.Movie

@Composable
fun MoviesHome(viewModel: MoviesHomeViewModel = hiltViewModel()) {
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()

    Text(modifier = Modifier, text = "Hello Movies! size: ${popularMovies.itemCount}")

    PopularMovies(popularMovies)

    if (popularMovies.loadState.refresh == LoadState.Loading) {
        LoadingView()
    }
}

@Composable
fun PopularMovies(popularMovies: LazyPagingItems<Movie>) {
    LazyColumn {
        items(
            count = popularMovies.itemCount,
            key = popularMovies.itemKey { it.id },
            contentType = popularMovies.itemContentType { "PopularMovie" }
        ) { index ->
            val item = popularMovies[index]
            item?.let { ItemMovie(it) }
        }
    }
}

@Composable
fun ItemMovie(movie: Movie) {
    val imageUrl = movie.getPosterUrl()

    Column {
        AsyncImage(
            model = imageUrl,
            contentDescription = "poster do filme ${movie.title}",
        )
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            color = Color.Red
        )
        Text(
            text = "Loading...",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}