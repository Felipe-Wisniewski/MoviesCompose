package com.wisnitech.data.repositories.account

import com.wisnitech.data.model.Movie
import com.wisnitech.data.remote.model.RequestWatchlist
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun saveMovieToWatchlist(requestWatchlist: RequestWatchlist): Flow<Unit>
    fun loadMoviesFromWatchlist(): Flow<List<Movie>>
}