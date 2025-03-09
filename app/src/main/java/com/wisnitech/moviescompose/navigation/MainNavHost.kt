package com.wisnitech.moviescompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wisnitech.moviescompose.ui.home.MoviesHomeRoute
import com.wisnitech.moviescompose.ui.home.moviesHomeNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    topAppBarConfig: (showBar: Boolean, showFilters: Boolean) -> Unit
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MoviesHomeRoute
    ) {

        moviesHomeNavGraph(navController) { showBar, showFilters ->
            topAppBarConfig(showBar, showFilters)
        }

    }
}