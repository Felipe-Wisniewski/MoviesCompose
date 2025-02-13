package com.wisnitech.moviescompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.wisnitech.moviescompose.navigation.MainNavHost

@Composable
fun MoviesApp() {

    val navController = rememberNavController()
    var showTopAppBar by remember { mutableStateOf(true) }

    Scaffold(

        topBar = {
            if (showTopAppBar) {
                MoviesTopAppBar(navController = navController)
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