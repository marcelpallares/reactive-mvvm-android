package com.mpgcode.reactivemvvm.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpgcode.reactivemvvm.data.interactors.GetQuoteInteractor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    context: Context,
    private val interactor: GetQuoteInteractor = GetQuoteInteractor(context),
    private val stateHandler: MainViewStateHandler = MainViewStateHandler()
): ViewModel() {
    private val viewEventsChannel = Channel<MainViewEvent>(Channel.BUFFERED)
    private val mutableState: MutableStateFlow<MainViewState> =
        MutableStateFlow(stateHandler.handleInitialState())

    val state = mutableState.asStateFlow()
    val viewEvents = viewEventsChannel.receiveAsFlow()

    init {
        fetchNewQuote()
    }

    fun emitAction(viewAction: MainViewAction) {
        when (viewAction) {
            MainViewAction.QuoteButtonClick -> fetchNewQuote()
            is MainViewAction.ShareButtonClick -> shareQuote(viewAction.author, viewAction.quote)
        }
    }

    private fun fetchNewQuote() {
        viewModelScope.launch {
            emitState(stateHandler.handleLoadingState(state.value))
            val quote = interactor.getQuote()
            emitState(stateHandler.handleSuccess(quote))
        }
    }


    private fun shareQuote(author: String, quote: String) {
        emitViewEvent(
            MainViewEvent.Share("$author: $quote")
        )
    }

    private fun emitState(state: MainViewState) {
        mutableState.value = state
    }

    private  fun emitViewEvent(event: MainViewEvent) = viewModelScope.launch {
        viewEventsChannel.send(event)
    }
}