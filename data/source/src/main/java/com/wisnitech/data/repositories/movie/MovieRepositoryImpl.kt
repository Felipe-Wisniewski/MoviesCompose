package com.wisnitech.data.repositories.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wisnitech.data.model.Movie
import com.wisnitech.data.model.MovieDetails
import com.wisnitech.data.remote.model.asExternalModel
import com.wisnitech.data.remote.source.movie.MovieNetworkDataSource
import com.wisnitech.data.remote.utils.ApiResult
import com.wisnitech.data.remote.utils.handleApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkDataSource: MovieNetworkDataSource
) : MovieRepository {

    override fun loadTopRatedMovies(): Flow<PagingData<Movie>> {
        return setPager(MoviesCall.TOP_RATED)
    }

    override fun loadPopularMovies(): Flow<PagingData<Movie>> {
        return setPager(MoviesCall.POPULAR)
    }

    override fun loadUpcomingMovies(): Flow<PagingData<Movie>> {
        return setPager(MoviesCall.UPCOMING)
    }

    private fun setPager(call: MoviesCall): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 30,
                prefetchDistance = 10
            ),
            pagingSourceFactory = {
                MoviePagingSource(call, networkDataSource)
            }
        ).flow
    }

    override fun loadMovieDetails(movieId: Int): Flow<MovieDetails> {
        return flow {
            try {
                val result = handleApiCall { networkDataSource.getMovieDetails(movieId) }

                when (result) {
                    is ApiResult.Success -> emit(result.data.asExternalModel())
                    is ApiResult.Error -> throw Exception("code:${result.code},message:${result.errorMsg}")
                    else -> throw Exception("An error occurred in the fun loadAllTrending")
                }

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}