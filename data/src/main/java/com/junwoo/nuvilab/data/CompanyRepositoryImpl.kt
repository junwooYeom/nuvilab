package com.junwoo.nuvilab.data

import android.net.ConnectivityManager
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.junwoo.nuvilab.database.PageDao
import com.junwoo.nuvilab.database.QueueDao
import com.junwoo.nuvilab.database.ResultDao
import com.junwoo.nuvilab.domain.Company
import com.junwoo.nuvilab.domain.CompanyRepository
import com.junwoo.nuvilab.network.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val service: NetworkService,
    private val resultDao: ResultDao,
    private val pageDao: PageDao,
    private val queueDao: QueueDao,
    private val connectivityManager: ConnectivityManager
): CompanyRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getCompanyList(): Flow<PagingData<Company>> = Pager(
        config = PagingConfig(pageSize = 10),
        remoteMediator = CompanyListMediator(
            service = service,
            cacheDao = resultDao,
            pageDao = pageDao,
            queueDao = queueDao,
            connectivityManager = connectivityManager
        ),
        pagingSourceFactory = {
            resultDao.getPagingData()
        },
    ).flow.map { it.map { item -> item.asDomainModel()} }
}