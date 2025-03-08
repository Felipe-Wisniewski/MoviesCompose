package com.wisnitech.moviescompose.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.wisnitech.moviescompose.ui.details.MovieDetailsScreen
import com.wisnitech.moviescompose.ui.search.SearchScreen
import com.wisnitech.moviescompose.ui.player.YouTubePlayerScreen
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

@Serializable
data class YouTubeScreenRoute(val movieKey: String)

fun NavGraphBuilder.moviesHomeNavGraph(
    navController: NavHostController,
    topAppBarConfig: (showBar: Boolean, showFilters:Boolean) -> Unit
) {

    composable<MoviesHomeRoute> {
        topAppBarConfig(true, true)

        MoviesHome { movieId ->
            navController.navigate(route = MovieDetailsRoute(movieId))
        }
    }

    composable<WatchlistScreenRoute> {
        topAppBarConfig(true, true)
        WatchlistScreen()
    }

    composable<SearchScreenRoute> {
        topAppBarConfig(false, false)

        SearchScreen()
    }

    composable<MovieDetailsRoute> {
        topAppBarConfig(true,false)

        MovieDetailsScreen { movieKey ->
            navController.navigate(route = YouTubeScreenRoute(movieKey))
        }
    }

    composable<YouTubeScreenRoute> { backStackEntry  ->
        topAppBarConfig(false,false)

        val route = backStackEntry.toRoute<YouTubeScreenRoute>()

        YouTubePlayerScreen(route.movieKey) {
            navController.navigateUp()
        }
    }
}