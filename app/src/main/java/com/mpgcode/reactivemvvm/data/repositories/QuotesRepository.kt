package com.mpgcode.reactivemvvm.data.repositories

import android.content.Context
import com.mpgcode.reactivemvvm.data.utils.JsonUtils.getJsonFromAssets
import com.mpgcode.reactivemvvm.data.models.Quote
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class QuotesRepository(
    private val context: Context,
    private val moshi: Moshi = Moshi.Builder().build()
) {
    fun getQuotesFromAssets(): List<Quote> {
        val jsonString = getJsonFromAssets(context, "quotes.json")
        return getQuotesAdapter().fromJson(jsonString) ?: emptyList()
    }

    private fun getQuotesAdapter() : JsonAdapter<List<Quote>> {
        val type = Types.newParameterizedType(List::class.java, Quote::class.java)
        return moshi.adapter(type)
    }
}