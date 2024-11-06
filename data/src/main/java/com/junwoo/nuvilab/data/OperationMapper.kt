package com.junwoo.nuvilab.data

import com.junwoo.nuvilab.database.QueueEntity
import com.junwoo.nuvilab.domain.FailedQueue

fun FailedQueue.toEntity(): QueueEntity = QueueEntity(
    id = id, pageNum = pageNum, timeStamp = timeStamp, retryCount = retryCount
)

fun QueueEntity.asDomainModel(): FailedQueue = FailedQueue(
    id = id,
    pageNum = pageNum,
    timeStamp = timeStamp,
    retryCount = retryCount
)