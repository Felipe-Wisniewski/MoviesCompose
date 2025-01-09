package com.wisnitech.moviescompose.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object MoviesHomeRoute

fun NavGraphBuilder.moviesHomeScreen() {
    composable<MoviesHomeRoute> { MoviesHome() }
}