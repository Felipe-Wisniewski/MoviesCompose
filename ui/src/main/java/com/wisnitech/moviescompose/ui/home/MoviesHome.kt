package com.wisnitech.moviescompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import java.util.UUID

@Composable
fun MoviesHome(
    viewModel: MoviesHomeViewModel = hiltViewModel(),
    onNavigateToDetails: (movieId: Int) -> Unit
) {

    val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val upcomingMovies = viewModel.upcomingMovies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HorizontalListMovies("Top Rated Movies", topRatedMovies) { movieId ->
            onNavigateToDetails(movieId)
        }

        HorizontalListMovies("Popular Movies", popularMovies) { movieId ->
            onNavigateToDetails(movieId)
        }

        HorizontalListMovies("Upcoming Movies", upcomingMovies) { movieId ->
            onNavigateToDetails(movieId)
        }
    }

}

@Composable
fun HorizontalListMovies(
    header: String,
    movies: LazyPagingItems<Movie>,
    movieId: (id: Int) -> Unit
) {

    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
        text = header
    )

    LazyRow(
        modifier = Modifier.height(200.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = movies.itemCount,
            key = movies.itemKey { "${it.id}-${UUID.randomUUID()}" },  // TODO("remove random")
            contentType = movies.itemContentType { "Movie" }
        ) { index ->
            val item = movies[index]
            item?.let {
                ItemMovie(it) { id ->
                    movieId(id)
                }
            }
        }

        if (movies.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            }
        }
    }
}

@Composable
fun ItemMovie(movie: Movie, movieId: (id: Int) -> Unit) {
    AsyncImage(
        modifier = Modifier
            .height(200.dp)
            .clickable { movieId(movie.id) },
        model = movie.getPosterUrl(),
        contentDescription = "poster do filme ${movie.title}",
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