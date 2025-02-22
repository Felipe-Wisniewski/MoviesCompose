package com.wisnitech.moviescompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wisnitech.data.model.Movie
import com.wisnitech.moviescompose.ui.R
import java.util.UUID

@Composable
fun LazyRowMovies(
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
            contentType = movies.itemContentType { it }
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
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(32.dp))
                }

            }
        }
    }
}

@Composable
fun ItemMovie(movie: Movie, movieId: (id: Int) -> Unit) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.posterUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder_poster),
        contentDescription = "${movie.title} poster",
        modifier = Modifier
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { movieId(movie.id) },
    )
}