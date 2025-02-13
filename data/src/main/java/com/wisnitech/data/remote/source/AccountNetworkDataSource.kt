package com.wisnitech.data.remote.source

import com.wisnitech.data.remote.model.RequestWatchlist
import com.wisnitech.data.remote.model.ResponseMovies
import retrofit2.Response

interface AccountNetworkDataSource {

    suspend fun postMovieToWatchlist(
        accountId: Int,
        requestWatchlist: RequestWatchlist
    ): Response<Unit>

    suspend fun getMoviesFromWatchlist(accountId: Int): Response<ResponseMovies>
}