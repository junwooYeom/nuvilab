package com.junwoo.nuvilab.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters

@Dao
@TypeConverters(EncryptionConverter::class)
interface QueueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(operation: QueueEntity)

    @Query("SELECT * FROM queueentity WHERE retryCount < :maxRetries")
    suspend fun getPendingOperations(maxRetries: Int): List<QueueEntity>

    @Query("DELETE FROM queueentity WHERE id = :id")
    suspend fun deleteOperation(id: Int)
}