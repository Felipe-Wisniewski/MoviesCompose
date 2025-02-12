package com.wisnitech.data.repositories.movies

import androidx.paging.PagingData
import com.wisnitech.data.model.Movie
import com.wisnitech.data.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getTopRatedMovies(): Flow<PagingData<Movie>>
    suspend fun getPopularMovies(): Flow<PagingData<Movie>>
    suspend fun getUpcomingMovies(): Flow<PagingData<Movie>>
    fun getMovieDetails(movieId: Int): Flow<MovieDetails>
}