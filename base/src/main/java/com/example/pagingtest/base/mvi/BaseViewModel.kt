package com.example.pagingtest.base.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<S : UiState, in I : UiEvent> : ViewModel() {

    abstract val state: Flow<S>

}