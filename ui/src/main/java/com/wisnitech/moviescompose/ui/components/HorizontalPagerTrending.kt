package com.wisnitech.moviescompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wisnitech.repository.model.Trending
import com.wisnitech.moviescompose.ui.R
import com.wisnitech.repository.model.MediaType
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun HorizontalPagerTrending(
    trending: List<Trending>,
    onNavigateToDetails: (mediaType: MediaType, mediaId: Int) -> Unit
) {
    val pagerState = rememberPagerState { trending.count() }

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            key = { trending[it].id }
        ) { index ->

            val item = trending[index]
            val itemName = item.title

            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.backdropUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder_backdrop),
                    contentDescription = itemName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToDetails(item.mediaType, item.id) },
                )
                Text(
                    text = itemName,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomStart),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        DotsIndicator(pagerState.pageCount, pagerState)
    }

    LaunchedEffect(pagerState) {
        val lastIndex = pagerState.pageCount - 1

        while (isActive) {
            delay(2000)

            val nextPage = if (pagerState.currentPage == lastIndex) 0
            else pagerState.currentPage + 1

            pagerState.animateScrollToPage(nextPage)
        }
    }

}

@Composable
fun DotsIndicator(pagerCount: Int, pagerState: PagerState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.White else Color.Gray
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}