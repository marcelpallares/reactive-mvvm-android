package com.mpgcode.reactivemvvm.view

import android.view.View

data class MainViewState(
    val author: String,
    val quote: String,
    val spinnerVisibility: VisibilityState,
    val isQuoteButtonEnabled: Boolean) {

    enum class VisibilityState(val value: Int) {
        VISIBLE(View.VISIBLE),
        GONE(View.GONE)
    }
}
