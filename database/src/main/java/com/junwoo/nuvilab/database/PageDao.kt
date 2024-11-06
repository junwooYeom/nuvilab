package com.junwoo.nuvilab.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters

@Dao
@TypeConverters(EncryptionConverter::class)
interface PageDao {
    @Query("SELECT MAX(pageNum) FROM pagekeyentity")
    suspend fun getLastPageNum(): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pageKey: PageKeyEntity)

    @Query("DELETE FROM pagekeyentity")
    suspend fun clearAll()

}