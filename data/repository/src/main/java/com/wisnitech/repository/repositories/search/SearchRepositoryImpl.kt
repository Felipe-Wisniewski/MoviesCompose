package com.wisnitech.repository.repositories.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wisnitech.source.remote.source.search.SearchNetworkDataSource
import com.wisnitech.repository.model.MovieResume
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchNetworkDataSource: SearchNetworkDataSource
) : SearchRepository {

    override fun searchMoviesAndTvShows(query: String): Flow<PagingData<MovieResume>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 60,
                prefetchDistance = 10
            ),
            pagingSourceFactory = { SearchPagingSource(query, searchNetworkDataSource) }
        ).flow
    }
}