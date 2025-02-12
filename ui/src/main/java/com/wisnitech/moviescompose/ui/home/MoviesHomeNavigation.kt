package com.wisnitech.moviescompose.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.wisnitech.moviescompose.ui.details.MovieDetailsScreen
import com.wisnitech.moviescompose.ui.search.SearchScreen
import com.wisnitech.moviescompose.ui.watchlist.WatchlistScreen
import kotlinx.serialization.Serializable

@Serializable
object MoviesHomeRoute

@Serializable
object WatchlistScreenRoute

@Serializable
object SearchScreenRoute

@Serializable
data class MovieDetailsRoute(val movieId: Int)

fun NavGraphBuilder.moviesHomeNavGraph(navController: NavHostController) {

    composable<MoviesHomeRoute> {
        MoviesHome { movieId ->
            navController.navigate(route = MovieDetailsRoute(movieId))
        }
    }

    composable<WatchlistScreenRoute> { WatchlistScreen() }

    composable<SearchScreenRoute> { SearchScreen() }

    composable<MovieDetailsRoute> {
        MovieDetailsScreen()
    }
}