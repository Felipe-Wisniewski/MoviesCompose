package com.wisnitech.data.remote.source

import com.wisnitech.data.models.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    suspend fun loadPopularMovies(@Query("page") page: Int): Response<ResponseMovies>

    @GET("movie/top_rated")
    suspend fun loadTopRatedMovies(@Query("page") page: Int): Response<ResponseMovies>
}