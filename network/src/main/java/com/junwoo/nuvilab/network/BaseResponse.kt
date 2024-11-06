package com.junwoo.nuvilab.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("header")
    val header: HeaderResponse,
    @SerialName("body")
    val body: T
)