package com.wisnitech.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wisnitech.data.models.Movie
import com.wisnitech.data.remote.source.MoviesNetworkDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val networkDataSource: MoviesNetworkDataSourceImpl
) : MoviesRepository {

    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 90,
                prefetchDistance = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(MoviesCall.POPULAR, networkDataSource)
            }
        ).flow
    }

    override suspend fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 90,
                prefetchDistance = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(MoviesCall.TOP_RATED, networkDataSource)
            }
        ).flow
    }

}