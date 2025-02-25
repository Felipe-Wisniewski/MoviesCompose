package com.wisnitech.data.remote.source.account

import com.wisnitech.data.remote.model.RequestWatchlist
import com.wisnitech.data.remote.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountApi {

    @POST("account/{accountId}/watchlist")
    suspend fun postMovieToWatchlist(
        @Path("accountId") accountId: Int,
        @Body requestWatchlist: RequestWatchlist
    ): Response<Unit>

    @GET("account/{accountId}/watchlist/movies")
    suspend fun getMoviesFromWatchlist(
        @Path("accountId") accountId: Int
    ): Response<ResponseMovies>

    // fun postTvShowToWatchlist()

    // fun getTvShowsWatchlist()

}