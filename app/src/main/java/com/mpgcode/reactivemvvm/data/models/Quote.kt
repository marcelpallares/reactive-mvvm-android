package com.mpgcode.reactivemvvm.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Quote(
    @Json(name = "_id") val id: String,
    val content: String,
    val author: String,
    val tags: List<String>,
    val authorId: String,
    val authorSlug: String,
    val length: Int
)
