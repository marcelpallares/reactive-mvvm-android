package com.mpgcode.reactivemvvm.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpgcode.reactivemvvm.data.interactors.GetQuoteInteractor
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    context: Context,
    private val interactor: GetQuoteInteractor = GetQuoteInteractor(context)
): ViewModel() {
    private val mutableState: MutableStateFlow<MainViewState> =
        MutableStateFlow(MainViewState(
            author = "Fetching author...",
            quote = "Fetching quote...",
            spinnerVisibility = MainViewState.VisibilityState.VISIBLE,
            isButtonEnabled = false
        ))

    val state = mutableState.asStateFlow()

    init {
        fetchNewQuote()
    }

    private fun fetchNewQuote() {
        viewModelScope.launch {
            val quote = interactor.getQuote()
            emitState(MainViewState(
                author = quote.author,
                quote = quote.content,
                spinnerVisibility = MainViewState.VisibilityState.GONE,
                isButtonEnabled = true
            ))
        }
    }

    private fun emitState(state: MainViewState) {
        mutableState.value = state
    }
}