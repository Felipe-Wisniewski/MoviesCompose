package com.wisnitech.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wisnitech.data.models.Movie
import com.wisnitech.data.remote.source.MoviesNetworkDataSource
import com.wisnitech.data.remote.utils.NetworkResult
import com.wisnitech.data.remote.utils.handleApiCall
import java.io.IOException

internal class MoviesPagingSource(
    private val call: MoviesCall,
    private val moviesDataSource: MoviesNetworkDataSource
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1

            val response = handleApiCall {
                when (call) {
                    MoviesCall.POPULAR -> moviesDataSource.loadPopularMovies(page)
                    MoviesCall.TOP_RATED -> moviesDataSource.loadTopRatedMovies(page)
                }
            }

            when (response) {
                is NetworkResult.SuccessResult -> {
                    LoadResult.Page(
                        data = response.data.results,
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

internal enum class MoviesCall {
    POPULAR,
    TOP_RATED
}