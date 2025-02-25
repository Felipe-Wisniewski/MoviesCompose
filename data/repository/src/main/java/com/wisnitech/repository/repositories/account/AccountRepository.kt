package com.wisnitech.repository.repositories.account

import com.wisnitech.repository.model.MovieResume
import com.wisnitech.data.remote.model.RequestWatchlist
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun saveMovieToWatchlist(requestWatchlist: RequestWatchlist): Flow<Unit>
    fun loadMoviesFromWatchlist(): Flow<List<MovieResume>>
}