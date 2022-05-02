package com.mpgcode.reactivemvvm.view

sealed class MainViewAction
object QuoteButtonClick : MainViewAction()
data class ShareButtonClick(
    val author: String,
    val quote: String
) : MainViewAction()
