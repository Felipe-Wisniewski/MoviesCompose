package com.wisnitech.data.remote.source

import com.wisnitech.data.remote.model.ResponseMovieDetails
import com.wisnitech.data.remote.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/top_rated")
    suspend fun loadTopRatedMovies(@Query("page") page: Int): Response<ResponseMovies>

    @GET("movie/popular")
    suspend fun loadPopularMovies(@Query("page") page: Int): Response<ResponseMovies>

    @GET("movie/upcoming")
    suspend fun loadUpcomingMovies(@Query("page") page: Int): Response<ResponseMovies>

    @GET("movie/{movieId}")
    suspend fun loadMovieDetails(@Path("movieId") movieId: Int): Response<ResponseMovieDetails>
}