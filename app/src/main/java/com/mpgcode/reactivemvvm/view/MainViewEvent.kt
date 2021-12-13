package com.mpgcode.reactivemvvm.view

sealed class MainViewEvent {
    data class Share(
        val text: String
    ) : MainViewEvent()
}