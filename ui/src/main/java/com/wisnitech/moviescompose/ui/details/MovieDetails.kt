package com.wisnitech.moviescompose.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MovieDetailsScreen(movieId: Int) {
    Column {
        Text("$movieId")
    }
}