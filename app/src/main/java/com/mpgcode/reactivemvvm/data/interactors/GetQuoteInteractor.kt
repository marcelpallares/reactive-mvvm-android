package com.mpgcode.reactivemvvm.data.interactors

import android.content.Context
import com.mpgcode.reactivemvvm.data.models.Quote
import com.mpgcode.reactivemvvm.data.repositories.QuotesRepository
import kotlinx.coroutines.*

class GetQuoteInteractor(
    context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: QuotesRepository = QuotesRepository(context),
    private val loadingIntervalMs: Long = 3000
) {
    suspend fun getQuote(): Quote {
        return withContext(ioDispatcher) {
            delay(loadingIntervalMs) // Wait to emulate a real network connection delay
            repository.getQuotesFromAssets().random()
        }
    }
}