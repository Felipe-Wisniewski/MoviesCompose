package com.wisnitech.moviescompose.ui.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wisnitech.moviescompose.ui.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MoviesTopAppBar(navController: NavHostController, scrollBehavior: TopAppBarScrollBehavior) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack by remember(backStackEntry) {
        mutableStateOf(navController.previousBackStackEntry != null)
    }

    Column {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(R.drawable.tmdb_alt_short),
                    contentDescription = null,
                    modifier = Modifier.width(100.dp)
                )
            },
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Localized description"
                    )
                }
            },
//            expandedHeight = TopAppBarDefaults.TopAppBarExpandedHeight,
//            windowInsets = TopAppBarDefaults.windowInsets,
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.secondary,
            ),
            scrollBehavior = scrollBehavior
        )

        TopAppBar(
            title = {
                RowFilters()
            },
//            expandedHeight = 40.dp,
//            expandedHeight = TopAppBarDefaults.TopAppBarExpandedHeight,
            windowInsets = WindowInsets(0.dp),
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.secondary,
            ),
            scrollBehavior = scrollBehavior
        )
    }
}

@Composable
fun RowFilters() {



    Row {
        TopBarFilterChip("Movies")
        Spacer(modifier = Modifier.size(8.dp))
        TopBarFilterChip("TV shows")
    }
}

@Composable
fun TopBarFilterChip(filter: String) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = { selected = !selected },
        label = {
            Text(filter)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}