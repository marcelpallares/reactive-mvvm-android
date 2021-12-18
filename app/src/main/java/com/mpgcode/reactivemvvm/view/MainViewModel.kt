package com.mpgcode.reactivemvvm.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class MainViewModel: ViewModel() {
    private val mutableState: MutableStateFlow<MainViewState> =
        MutableStateFlow(MainViewState(
            author = "Fetching author...",
            quote = "Fetching quote...",
            spinnerVisibility = MainViewState.VisibilityState.VISIBLE,
            isButtonEnabled = false
        ))

    val state = mutableState.asStateFlow()
}