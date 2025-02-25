package com.wisnitech.moviescompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wisnitech.data.model.MovieResume
import com.wisnitech.moviescompose.ui.R
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ListVideosWithResume(videos: LazyPagingItems<MovieResume>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = videos.itemCount,
            key = videos.itemKey { it.id },
            contentType = videos.itemContentType { it }
        ) { index ->
            val item = videos[index]
            item?.let { ItemVideoWithResume(it) }
        }

        if (videos.loadState.append == LoadState.Loading) {
            item {
                LoadingView()
            }
        }
    }
}

@Composable
fun ItemVideoWithResume(video: MovieResume) {
    Row(
        modifier = Modifier.height(100.dp),
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
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Column {
            Text(text = video.title)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = video.overview, fontSize = 11.sp, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListVideosWithResumePreview() {
    val pagingData = PagingData.from(listMovieResume)
    val fakeDataFlow = MutableStateFlow(pagingData)
    val lazyPagingItems = fakeDataFlow.collectAsLazyPagingItems()
    ListVideosWithResume(lazyPagingItems)
}