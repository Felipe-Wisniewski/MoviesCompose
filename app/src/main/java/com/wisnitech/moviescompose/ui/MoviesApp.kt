package com.wisnitech.moviescompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.rememberNavController
import com.wisnitech.moviescompose.navigation.MainNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesApp() {
    val navController = rememberNavController()
    var showTopAppBar by remember { mutableStateOf(true) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (showTopAppBar) {
                MoviesTopAppBar(navController = navController, scrollBehavior)
            }
        },

        bottomBar = {
            MoviesBottomAppBar(navController = navController)
        }

    ) { innerPadding ->

        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            showTopAppBar = { showTopAppBar = it }
        )
    }
}