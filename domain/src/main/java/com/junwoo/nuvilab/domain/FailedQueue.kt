package com.junwoo.nuvilab.domain

import kotlinx.datetime.Clock

data class FailedQueue(
    val id: Int = 0,
    val pageNum: Int,
    val timeStamp: Long = Clock.System.now().toEpochMilliseconds(),
    val retryCount: Int = 0
)