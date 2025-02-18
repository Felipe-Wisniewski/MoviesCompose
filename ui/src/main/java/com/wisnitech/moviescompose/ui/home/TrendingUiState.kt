package com.wisnitech.moviescompose.ui.home

import com.wisnitech.data.model.Trending

sealed interface TrendingUiState {
    data object Loading : TrendingUiState
    data class Success(val trending: List<Trending> = emptyList()) : TrendingUiState
}
