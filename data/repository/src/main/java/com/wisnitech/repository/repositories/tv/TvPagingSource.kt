package com.wisnitech.repository.repositories.tv

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wisnitech.repository.model.Movie
import com.wisnitech.repository.model.asExternalModel
import com.wisnitech.repository.utils.ApiResult
import com.wisnitech.repository.utils.handleApiCall
import com.wisnitech.source.remote.model.NetworkMovie
import com.wisnitech.source.remote.source.tv.TvNetworkDataSource
import java.io.IOException

internal class TvPagingSource(
    private val call: TvCall,
    private val tvNetworkDataSource: TvNetworkDataSource
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1

            val response = handleApiCall {
                when (call) {
                    TvCall.POPULAR -> tvNetworkDataSource.getPopularTvShows(page)
                    TvCall.TOP_RATED -> tvNetworkDataSource.getTopRatedTvShows(page)
                }
            }

            when (response) {
                is ApiResult.Success -> {
                    LoadResult.Page(
                        data = response.data.results.map(NetworkMovie::asExternalModel),
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

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}

internal enum class TvCall {
    POPULAR,
    TOP_RATED
}
