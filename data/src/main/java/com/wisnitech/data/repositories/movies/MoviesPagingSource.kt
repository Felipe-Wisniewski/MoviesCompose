package com.wisnitech.data.repositories.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wisnitech.data.model.Movie
import com.wisnitech.data.remote.model.NetworkMovie
import com.wisnitech.data.remote.model.asExternalModel
import com.wisnitech.data.remote.source.MoviesNetworkDataSource
import com.wisnitech.data.remote.utils.ApiResult
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
                    MoviesCall.TOP_RATED -> moviesDataSource.loadTopRatedMovies(page)
                    MoviesCall.POPULAR -> moviesDataSource.loadPopularMovies(page)
                    MoviesCall.UPCOMING -> moviesDataSource.loadUpcomingMovies(page)
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

internal enum class MoviesCall {
    TOP_RATED,
    POPULAR,
    UPCOMING
}