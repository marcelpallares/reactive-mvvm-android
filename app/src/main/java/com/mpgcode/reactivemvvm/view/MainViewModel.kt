package com.mpgcode.reactivemvvm.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpgcode.reactivemvvm.data.interactors.GetQuoteInteractor
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    context: Context,
    private val interactor: GetQuoteInteractor = GetQuoteInteractor(context),
    private val stateHandler: MainViewStateHandler = MainViewStateHandler()
): ViewModel() {
    private val mutableState: MutableStateFlow<MainViewState> =
        MutableStateFlow(stateHandler.handleInitialState())

    val state = mutableState.asStateFlow()

    init {
        fetchNewQuote()
    }

    private fun fetchNewQuote() {
        viewModelScope.launch {
            val quote = interactor.getQuote()
            emitState(stateHandler.handleSuccess(quote))
        }
    }

    private fun emitState(state: MainViewState) {
        mutableState.value = state
    }
}