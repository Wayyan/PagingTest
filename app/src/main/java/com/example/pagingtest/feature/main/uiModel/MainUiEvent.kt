package com.example.pagingtest.feature.main.uiModel

import com.example.pagingtest.base.mvi.UiEvent

sealed class MainUiEvent : UiEvent {
    data object LoadBeers : MainUiEvent()
    data class ErrorLoadingBeers(val error: Throwable, val isAtRefresh: Boolean) : MainUiEvent()
    data object DismissAlert : MainUiEvent()
}
