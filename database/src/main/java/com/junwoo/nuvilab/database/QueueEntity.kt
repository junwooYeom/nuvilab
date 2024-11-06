package com.junwoo.nuvilab.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock

@Entity
data class QueueEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pageNum: Int,
    val timeStamp: Long = Clock.System.now().toEpochMilliseconds(),
    val retryCount: Int = 0
)

