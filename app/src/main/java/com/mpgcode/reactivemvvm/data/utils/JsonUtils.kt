package com.mpgcode.reactivemvvm.data.utils

import android.content.Context

object JsonUtils {
    fun getJsonFromAssets(context: Context, fileName: String) : String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}