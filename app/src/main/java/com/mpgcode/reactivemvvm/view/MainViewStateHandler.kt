package com.mpgcode.reactivemvvm.view

import com.mpgcode.reactivemvvm.data.models.Quote
import com.mpgcode.reactivemvvm.view.MainViewState.VisibilityState

class MainViewStateHandler {
    fun handleInitialState() = MainViewState(
        author = "Fetching author...",
        quote = "Fetching quote...",
        spinnerVisibility = VisibilityState.VISIBLE,
        isButtonEnabled = false
    )

    fun handleSuccess(
        quote: Quote
    ) = MainViewState(
        author = quote.author,
        quote = quote.content,
        spinnerVisibility = VisibilityState.GONE,
        isButtonEnabled = true
    )
}