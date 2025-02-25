package com.wisnitech.repository.repositories.search

import androidx.paging.PagingData
import com.wisnitech.repository.model.MovieResume
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchMoviesAndTvShows(query: String): Flow<PagingData<MovieResume>>
}