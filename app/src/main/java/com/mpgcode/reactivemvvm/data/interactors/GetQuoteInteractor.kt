package com.mpgcode.reactivemvvm.data.interactors

import android.content.Context
import com.mpgcode.reactivemvvm.data.models.Quote
import com.mpgcode.reactivemvvm.data.repositories.QuotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

class GetQuoteInteractor(
    context: Context,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    externalScope: CoroutineScope = CoroutineScope(ioDispatcher),
    private val repository: QuotesRepository = QuotesRepository(context),
    private val loadingIntervalMs: Long = 3000
) {
    val randomQuoteFlow: Flow<Quote> = flow {
        delay(loadingIntervalMs) // Wait to emulate a real network connection delay
        emit(repository.getQuotesFromAssets().random())
    }.shareIn(
        scope = externalScope,
        replay = 1,
        started = SharingStarted.WhileSubscribed(5000)
    )
}