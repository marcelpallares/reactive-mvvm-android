package com.mpgcode.reactivemvvm.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpgcode.reactivemvvm.data.interactors.GetQuoteInteractor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    context: Context,
    private val interactor: GetQuoteInteractor = GetQuoteInteractor(context),
    private val stateHandler: MainViewStateHandler = MainViewStateHandler()
) : ViewModel() {
    private val viewActions = MutableSharedFlow<MainViewAction>()
    private val mutableState: MutableStateFlow<MainViewState> =
        MutableStateFlow(stateHandler.handleInitialState())

    val state = mutableState.asStateFlow()

    init {
        listenToViewActions()
        fetchNewQuote()
    }

    fun emitAction(viewAction: MainViewAction) {
        viewModelScope.launch { viewActions.emit(viewAction) }
    }

    private fun listenToViewActions() = viewModelScope.launch {
        viewActions.collect { handleAction(it) }
    }

    private fun handleAction(action: MainViewAction) {
        when (action) {
            MainViewAction.QuoteButtonClick -> fetchNewQuote()
            MainViewAction.ShareButtonClick -> Unit
        }
    }

    private fun fetchNewQuote() {
        viewModelScope.launch {
            emitState(stateHandler.handleLoadingState(state.value))
            val quote = interactor.getQuote()
            emitState(stateHandler.handleSuccess(quote))
        }
    }

    private fun emitState(state: MainViewState) {
        mutableState.value = state
    }
}