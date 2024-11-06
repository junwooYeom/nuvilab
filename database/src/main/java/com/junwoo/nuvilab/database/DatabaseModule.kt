package com.junwoo.nuvilab.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesNuvilabDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NuvilabDatabase::class.java, "nuvilab.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesPageDao(database: NuvilabDatabase) = database.pageDao()

    @Provides
    @Singleton
    fun providesResultDao(database: NuvilabDatabase) = database.resultDao()

    @Provides
    @Singleton
    fun providesQueueDao(database: NuvilabDatabase) = database.queueDao()
}