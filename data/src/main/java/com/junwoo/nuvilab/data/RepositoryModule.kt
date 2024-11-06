package com.junwoo.nuvilab.data

import com.junwoo.nuvilab.domain.CompanyRepository
import com.junwoo.nuvilab.domain.OperationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsCompanyRepository(impl: CompanyRepositoryImpl): CompanyRepository

    @Binds
    @Singleton
    fun bindsOperationRepository(impl: OperationRepositoryImpl): OperationRepository
}