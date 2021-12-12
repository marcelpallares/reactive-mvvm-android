package com.mpgcode.reactivemvvm.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpgcode.reactivemvvm.data.interactors.GetQuoteInteractor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    context: Context,
    private val interactor: GetQuoteInteractor = GetQuoteInteractor(context)
) : ViewModel() {
    init {
        viewModelScope.launch {
            interactor.randomQuoteFlow.collect {
                println(it.content)
            }
        }
    }
}