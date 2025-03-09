package com.wisnitech.moviescompose.ui.details.tv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TvDetailsScreen(
    viewModel: TvDetailsViewModel = hiltViewModel()
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Todo()")
    }

}