package com.wisnitech.moviescompose.ui.home

sealed interface HomeUiState {
    data object Loading : HomeUiState

}
