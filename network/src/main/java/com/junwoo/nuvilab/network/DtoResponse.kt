package com.junwoo.nuvilab.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DtoResponse<T>(
    @SerialName("item")
    val data: T
)
