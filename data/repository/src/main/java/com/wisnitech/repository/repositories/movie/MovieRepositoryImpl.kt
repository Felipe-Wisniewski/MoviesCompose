package com.wisnitech.repository.repositories.movie

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wisnitech.source.remote.source.movie.MovieNetworkDataSource
import com.wisnitech.repository.utils.ApiResult
import com.wisnitech.repository.utils.handleApiCall
import com.wisnitech.repository.model.Movie
import com.wisnitech.repository.model.MovieDetails
import com.wisnitech.repository.model.asExternalModel
import com.wisnitech.source.local.model.LocalWatchlist
import com.wisnitech.source.local.source.WatchlistLocalDataSource
import com.wisnitech.source.remote.model.ResponseMovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkDataSource: MovieNetworkDataSource,
    private val watchlistLocalDataSource: WatchlistLocalDataSource
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

    override fun loadMovieDetails(movieId: Int): Flow<MovieDetails> = combine(
        watchlistLocalDataSource.loadLocalWatchlistById(movieId),
        loadDetails(movieId)
    ) { watchList, movie ->
        movie.asExternalModel(watchList != null)
    }

    private fun loadDetails(movieId: Int): Flow<ResponseMovieDetails> {
        return flow {
            try {
                val result = handleApiCall { networkDataSource.getMovieDetails(movieId) }

                when (result) {
                    is ApiResult.Success -> {
                        emit(result.data)
                    }

                    is ApiResult.Error -> throw Exception("code:${result.code},message:${result.errorMsg}")
                    else -> throw Exception("An error occurred in the fun loadAllTrending")
                }

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}