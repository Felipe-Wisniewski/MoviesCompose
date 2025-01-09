package com.wisnitech.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wisnitech.data.models.Movie
import com.wisnitech.data.remote.source.MoviesNetworkDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val networkDataSource: MoviesNetworkDataSource
) : MoviesRepository {

    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> {
        return setPager(MoviesCall.POPULAR)
    }

    override suspend fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return setPager(MoviesCall.TOP_RATED)
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
}