package com.mpgcode.reactivemvvm.view

sealed class MainViewAction
object QuoteButtonClick : MainViewAction()
object ShareButtonClick : MainViewAction()
