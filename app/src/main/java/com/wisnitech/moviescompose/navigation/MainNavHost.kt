package com.wisnitech.moviescompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wisnitech.moviescompose.ui.home.MoviesHomeRoute
import com.wisnitech.moviescompose.ui.home.moviesHomeNavGraph

@Composable
fun MainNavHost(
    navController: NavHostController,
    showTopAppBar: (show: Boolean) -> Unit,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = MoviesHomeRoute,
        modifier = modifier
    ) {

        moviesHomeNavGraph(navController) { showTopAppBar(it) }

    }
}