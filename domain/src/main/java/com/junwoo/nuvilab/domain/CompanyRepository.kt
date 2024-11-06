package com.junwoo.nuvilab.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CompanyRepository {

    fun getCompanyList(): Flow<PagingData<Company>>
}