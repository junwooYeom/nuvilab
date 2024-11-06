package com.junwoo.nuvilab.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeaderResponse(
    @SerialName("resultCode")
    val resultCode: String,
    @SerialName("resultMessage")
    val resultMessage: String
)