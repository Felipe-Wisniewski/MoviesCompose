package com.wisnitech.moviescompose.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.wisnitech.moviescompose.ui.details.movie.MovieDetailsScreen
import com.wisnitech.moviescompose.ui.details.tv.TvDetailsScreen
import com.wisnitech.moviescompose.ui.search.SearchScreen
import com.wisnitech.moviescompose.ui.player.YouTubePlayerScreen
import com.wisnitech.moviescompose.ui.watchlist.WatchlistScreen
import com.wisnitech.repository.model.MediaType
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
data class TvDetailsRoute(val tvId: Int)

@Serializable
data class YouTubeScreenRoute(val movieKey: String)

fun NavGraphBuilder.moviesHomeNavGraph(
    navController: NavHostController,
    topAppBarConfig: (showBar: Boolean, showFilters: Boolean) -> Unit
) {

    composable<MoviesHomeRoute> {
        topAppBarConfig(true, true)

        MoviesHome { mediaType, mediaId ->
            when (mediaType) {
                MediaType.MOVIE -> navController.navigate(route = MovieDetailsRoute(mediaId))
                MediaType.TV -> navController.navigate(route = TvDetailsRoute(mediaId))
                MediaType.PERSON -> TODO()
            }
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
        topAppBarConfig(true, false)

        MovieDetailsScreen { movieKey ->
            navController.navigate(route = YouTubeScreenRoute(movieKey))
        }
    }

    composable<TvDetailsRoute> {
        topAppBarConfig(true,false)

        TvDetailsScreen()
    }

    composable<YouTubeScreenRoute> { backStackEntry ->
        topAppBarConfig(false, false)

        val route = backStackEntry.toRoute<YouTubeScreenRoute>()

        YouTubePlayerScreen(route.movieKey) {
            navController.navigateUp()
        }
    }
}