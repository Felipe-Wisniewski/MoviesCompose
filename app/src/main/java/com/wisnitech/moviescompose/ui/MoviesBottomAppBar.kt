package com.wisnitech.moviescompose.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wisnitech.moviescompose.ui.home.MoviesHomeRoute
import com.wisnitech.moviescompose.ui.home.SearchScreenRoute
import com.wisnitech.moviescompose.ui.home.WatchlistScreenRoute

@Composable
fun MoviesBottomAppBar(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(false) }

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomBarItems.forEach { bottomRoute ->

            selectedItem =
                currentDestination?.hierarchy?.any { it.hasRoute(bottomRoute.route::class) } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        if (selectedItem) bottomRoute.iconSelected else bottomRoute.iconUnselected,
                        contentDescription = bottomRoute.label
                    )
                },
                label = { Text(bottomRoute.label) },
                selected = selectedItem,
                onClick = {
                    navController.navigate(bottomRoute.route) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

        }
    }
}

val bottomBarItems = listOf(
    BottomBarRoute(
        "Home",
        MoviesHomeRoute,
        Icons.Filled.Home,
        Icons.Outlined.Home
    ),
    BottomBarRoute(
        "Watchlist",
        WatchlistScreenRoute,
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder
    ),
    BottomBarRoute(
        "Search",
        SearchScreenRoute,
        Icons.Filled.Search,
        Icons.Outlined.Search
    )
)

data class BottomBarRoute<T : Any>(
    val label: String,
    val route: T,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector
)