package com.wisnitech.data.repositories.movies

import androidx.paging.PagingData
import com.wisnitech.data.model.Movie
import com.wisnitech.data.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getTopRatedMovies(): Flow<PagingData<Movie>>
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun getUpcomingMovies(): Flow<PagingData<Movie>>
    fun getMovieDetails(movieId: Int): Flow<MovieDetails>
}