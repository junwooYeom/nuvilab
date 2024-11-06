package com.junwoo.nuvilab.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters

@Dao
@TypeConverters(EncryptionConverter::class)
interface ResultDao {
    @Query("SELECT DISTINCT * FROM resultentity")
    fun getPagingData(): PagingSource<Int, ResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllData(items: List<ResultEntity>)

    @Query("DELETE FROM ResultEntity")
    suspend fun deleteCacheTable()
}