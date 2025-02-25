package com.wisnitech.data.repositories.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wisnitech.data.model.MovieResume
import com.wisnitech.data.remote.model.NetworkMovie
import com.wisnitech.data.remote.model.asExternalResumeModel
import com.wisnitech.data.remote.source.search.SearchNetworkDataSource
import com.wisnitech.data.remote.utils.ApiResult
import com.wisnitech.data.remote.utils.handleApiCall
import java.io.IOException

class SearchPagingSource(
    private val query: String,
    private val searchNetworkDataSource: SearchNetworkDataSource
) : PagingSource<Int, MovieResume>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResume> {
        return try {
            val page = params.key ?: 1

            val response =
                handleApiCall { searchNetworkDataSource.searchMoviesAndTvShows(query, page) }

            when (response) {
                is ApiResult.Success -> {
                    LoadResult.Page(
                        data = response.data.results.map(NetworkMovie::asExternalResumeModel),
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (page == response.data.totalPages) null else page + 1
                    )
                }

                else -> LoadResult.Error(Exception(""))
            }

        } catch (e: IOException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResume>): Int? {
        return state.anchorPosition
    }
}