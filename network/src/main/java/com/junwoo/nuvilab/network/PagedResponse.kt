package com.junwoo.nuvilab.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagedResponse<T>(
    @SerialName("numOfRows")
    val numOfRows: String,
    @SerialName("pageNo")
    val pageNo: String,
    @SerialName("totalCount")
    val totalCount: String,
    @SerialName("items")
    val items: List<DtoResponse<T>>
)