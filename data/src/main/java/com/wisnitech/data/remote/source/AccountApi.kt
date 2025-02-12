package com.wisnitech.data.remote.source

import retrofit2.http.GET
import retrofit2.http.POST

interface AccountApi {

    @POST("account/{account_id}/watchlist")
    fun postToWatchlist()

    @GET("account/{account_id}/watchlist/movies")
    fun getMoviesWatchlist()

//    fun getTvShowsWatchlist()
}