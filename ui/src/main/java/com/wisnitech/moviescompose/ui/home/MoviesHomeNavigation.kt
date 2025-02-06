package com.wisnitech.moviescompose.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.wisnitech.moviescompose.ui.details.MovieDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
object MoviesHomeRoute

@Serializable
data class MovieDetails(val movieId: Int)

fun NavGraphBuilder.moviesHomeNavGraph(navController: NavHostController) {

    composable<MoviesHomeRoute> {
        MoviesHome { movieId ->
            navController.navigate(route = MovieDetails(movieId))
        }
    }

    composable<MovieDetails> { backStackEntry ->
        val movieDetails : MovieDetails = backStackEntry.toRoute()
        MovieDetailsScreen(movieDetails.movieId)
    }
}