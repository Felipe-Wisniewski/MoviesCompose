package com.wisnitech.data.repositories.movies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wisnitech.data.models.Movie
import com.wisnitech.data.models.MovieDetails
import com.wisnitech.data.remote.source.MoviesNetworkDataSource
import com.wisnitech.data.remote.utils.ApiResult
import com.wisnitech.data.remote.utils.handleApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val networkDataSource: MoviesNetworkDataSource
) : MoviesRepository {

    override suspend fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return setPager(MoviesCall.TOP_RATED)
    }

    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> {
        return setPager(MoviesCall.POPULAR)
    }

    override suspend fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return setPager(MoviesCall.UPCOMING)
    }

    private fun setPager(call: MoviesCall): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 90,
                prefetchDistance = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(call, networkDataSource)
            }
        ).flow
    }

    override fun getMovieDetails(movieId: Int): Flow<MovieDetails> {
        return flow {
            try {
                val result = handleApiCall { networkDataSource.loadMovieDetails(movieId) }

                when (result) {
                    is ApiResult.Success -> emit(result.data)
                    is ApiResult.Error -> throw Exception("code:${result.code},message:${result.errorMsg}")
                    else -> throw Exception("An error occurred in the fun loadAllTrending")
                }

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}