package com.example.pagingtest.feature.main

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pagingtest.base.mvi.BaseViewModel
import com.example.pagingtest.base.mvi.Reducer
import com.example.pagingtest.domain.beer.usecase.GetBeerPagingDataFlow
import com.example.pagingtest.feature.main.uiModel.MainAlertState
import com.example.pagingtest.feature.main.uiModel.MainUiEvent
import com.example.pagingtest.feature.main.uiModel.MainUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val getBeerPagingDataFlow: GetBeerPagingDataFlow,
) :
    BaseViewModel<MainUiState, MainUiEvent>() {

    private val reducer = MainReducer()

    override val state: Flow<MainUiState>
        get() = reducer.state

    private fun sendEvent(event: MainUiEvent) {
        reducer.sendEvent(event)
    }

    fun loadBeers() {
        sendEvent(MainUiEvent.LoadBeers)
    }

    fun errorLoadingBeers(e: Throwable, isAtRefresh: Boolean = false) {
        sendEvent(MainUiEvent.ErrorLoadingBeers(e, isAtRefresh))
    }

    fun dismissedAlert() {
        sendEvent(MainUiEvent.DismissAlert)
    }

    private inner class MainReducer : Reducer<MainUiState, MainUiEvent>(MainUiState()) {

        override fun reduce(oldState: MainUiState, event: MainUiEvent) {
            when (event) {
                MainUiEvent.LoadBeers -> {
                    viewModelScope.launch {
                        getBeerPagingDataFlow.execute(Unit)
                            .cachedIn(viewModelScope).collect {
                                setState(oldState.copy(beers = it))
                            }
                    }
                }

                is MainUiEvent.ErrorLoadingBeers -> {
                    setState(
                        oldState.copy(
                            alertState = MainAlertState.PromptLoadingBeersError(
                                event.error.localizedMessage ?: "Something Went Wrong",
                                event.isAtRefresh
                            )
                        )
                    )
                }

                MainUiEvent.DismissAlert -> {
                    setState(oldState.copy(alertState = null))
                }
            }
        }

    }
}