package com.wisnitech.moviescompose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    val trendingUiState by viewModel.trendingUiState.collectAsStateWithLifecycle()
    val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val upcomingMovies = viewModel.upcomingMovies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TrendingCarrousel(trendingUiState)

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingCarrousel(trendingUiState: TrendingUiState) {

    var size by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    when (trendingUiState) {
        is TrendingUiState.Loading -> LoadingView()

        is TrendingUiState.Success -> {
            val trendingMovies = trendingUiState.trending

            HorizontalUncontainedCarousel(
                state = rememberCarouselState { trendingMovies.count() },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        size = with(density) {
                            coordinates.size.width.toDp()
                        }
                    },
                itemWidth = size
            ) { i ->
                val item = trendingMovies[i]
                val itemName = item.getName()

                Box(modifier = Modifier.background(Color.Green)) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = item.getBackdropUrl(),
                        contentDescription = itemName,
                    )
                    Text(itemName)
                }
            }
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