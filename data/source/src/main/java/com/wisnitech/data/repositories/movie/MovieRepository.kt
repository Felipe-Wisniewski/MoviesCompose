package com.wisnitech.data.repositories.movie

import androidx.paging.PagingData
import com.wisnitech.data.model.Movie
import com.wisnitech.data.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun loadTopRatedMovies(): Flow<PagingData<Movie>>
    fun loadPopularMovies(): Flow<PagingData<Movie>>
    fun loadUpcomingMovies(): Flow<PagingData<Movie>>
    fun loadMovieDetails(movieId: Int): Flow<MovieDetails>
}