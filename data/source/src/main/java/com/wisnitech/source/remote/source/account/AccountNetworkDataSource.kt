package com.wisnitech.source.remote.source.account

import com.wisnitech.source.remote.model.RequestWatchlist
import com.wisnitech.source.remote.model.ResponseMovies
import retrofit2.Response

interface AccountNetworkDataSource {

    suspend fun postMovieToWatchlist(
        accountId: Int,
        requestWatchlist: RequestWatchlist
    ): Response<Unit>

    suspend fun getMoviesFromWatchlist(accountId: Int): Response<ResponseMovies>
}