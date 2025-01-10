package com.wisnitech.moviescompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()

    // verticalScroll(scrollState)
    Column(modifier = Modifier.fillMaxSize()) {

        HorizontalListMovies("Popular", popularMovies)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )

        HorizontalListMovies("Top Rated", topRatedMovies)

    }
}

@Composable
fun HorizontalListMovies(header: String, movies: LazyPagingItems<Movie>) {
    val textHeader by remember(movies.itemCount) {
        mutableStateOf("$header: ${movies.itemCount}")
    }

    Text(modifier = Modifier.padding(start = 16.dp, end = 16.dp), text = textHeader)

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
    )

    LazyRow(
        modifier = Modifier.height(200.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = movies.itemCount,
            key = movies.itemKey { it.id },
            contentType = movies.itemContentType { "PopularMovie" }
        ) { index ->
            val item = movies[index]
            item?.let { ItemMovie(it.getPosterUrl(), it.title) }
        }

        if (movies.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            }
        }
    }
}

@Composable
fun ItemMovie(imageUrl: String?, description: String) {
    AsyncImage(
        modifier = Modifier.height(200.dp),
        model = imageUrl,
        contentDescription = "poster do filme $description",
    )
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