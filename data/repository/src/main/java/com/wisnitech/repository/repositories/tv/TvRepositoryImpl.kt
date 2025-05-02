package com.wisnitech.repository.repositories.tv

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wisnitech.repository.model.Movie
import com.wisnitech.source.remote.source.tv.TvNetworkDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val networkDataSource: TvNetworkDataSource
) : TvRepository {

    override fun loadPopularTvShows(): Flow<PagingData<Movie>> {
        return setPager(TvCall.POPULAR)
    }

    override fun loadTopRatedTvShows(): Flow<PagingData<Movie>> {
        return setPager(TvCall.TOP_RATED)
    }

    private fun setPager(call: TvCall): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 30,
                prefetchDistance = 10
            ),
            pagingSourceFactory = {
                TvPagingSource(call, networkDataSource)
            }
        ).flow
    }
}