package com.wisnitech.moviescompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.wisnitech.moviescompose.navigation.MainNavHost

@Composable
fun MoviesApp() {
    val navController = rememberNavController()

    Scaffold {
        MainNavHost(navController, Modifier.padding(it))
    }
}