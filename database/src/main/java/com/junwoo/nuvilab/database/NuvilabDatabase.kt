package com.junwoo.nuvilab.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PageKeyEntity::class, ResultEntity::class, QueueEntity::class], version = 2, exportSchema = false)
@TypeConverters(EncryptionConverter::class)
abstract class NuvilabDatabase: RoomDatabase() {
    abstract fun pageDao(): PageDao

    abstract fun resultDao(): ResultDao

    abstract fun queueDao(): QueueDao
}