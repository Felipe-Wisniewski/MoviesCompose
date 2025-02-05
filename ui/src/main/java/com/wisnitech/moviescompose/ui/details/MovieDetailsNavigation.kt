package com.wisnitech.moviescompose.ui.details

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(val movieId: Int)

fun NavGraphBuilder.movieDetailsScreen() {
    composable<MovieDetails> { backStackEntry ->
        val movieDetails : MovieDetails = backStackEntry.toRoute()
        MovieDetailsScreen(movieDetails.movieId)
    }
}