package com.wisnitech.moviescompose.ui.home

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MoviesHome(viewModel: MoviesHomeViewModel = hiltViewModel()) {
    val popular = viewModel.popularMovies.collectAsLazyPagingItems()

    Log.d("FLMWG","popular.size:${popular.itemCount}")

    Text(modifier = Modifier, text = "Hello Movies! size: ${popular.itemCount}")
}