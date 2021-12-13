package com.mpgcode.reactivemvvm.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpgcode.reactivemvvm.data.interactors.GetQuoteInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    context: Context,
    private val interactor: GetQuoteInteractor = GetQuoteInteractor(context),
    private val stateHandler: MainViewStateHandler = MainViewStateHandler()
) : ViewModel() {
    private val mutableState: MutableStateFlow<MainViewState> =
        MutableStateFlow(stateHandler.handleInitialState())

    val state = mutableState.asStateFlow()

    init {
        fetchNewQuote()
    }

    private fun fetchNewQuote() {
        emitState(stateHandler.handleLoadingState(state.value))
        viewModelScope.launch {
            interactor.randomQuoteFlow.collect {
                emitState(stateHandler.handleSuccess(it))
            }
        }
    }

    private fun emitState(state: MainViewState) {
        mutableState.value = state
    }
}