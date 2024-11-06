package com.junwoo.nuvilab.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PageKeyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pageNum: Int
)