package com.example.pagingtest.feature.main.uiModel

import androidx.paging.PagingData
import com.example.pagingtest.base.mvi.UiState
import com.example.pagingtest.domain.beer.model.BeerModel

data class MainUiState(
    val beers: PagingData<BeerModel>? = null,
    val alertState: MainAlertState? = null
) : UiState

sealed class MainAlertState {
    data class PromptLoadingBeersError(val message: String, val isImportant: Boolean) :
        MainAlertState()
}