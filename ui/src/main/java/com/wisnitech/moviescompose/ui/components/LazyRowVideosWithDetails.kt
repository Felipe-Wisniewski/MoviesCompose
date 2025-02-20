package com.wisnitech.moviescompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wisnitech.data.model.MovieResume
import com.wisnitech.moviescompose.ui.R

@Composable
fun ListVideosWithResume(videos: LazyPagingItems<MovieResume>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = videos.itemCount,
            key = videos.itemKey { it.id }
        ) { index ->
            val item = videos[index]
            item?.let { ItemVideoWithResume(it) }
        }
    }
}

@Composable
fun ItemVideoWithResume(video: MovieResume) {
    Row(
        modifier = Modifier.height(200.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(video.posterUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder_poster),
                error = painterResource(R.drawable.placeholder_poster),
                contentDescription = "poster ${video.title}",
                modifier = Modifier
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { /* TODO */ }
            )
        }

        Column {
            Text(text = video.title)
            Text(text = video.overview ?: "")
        }
    }
}