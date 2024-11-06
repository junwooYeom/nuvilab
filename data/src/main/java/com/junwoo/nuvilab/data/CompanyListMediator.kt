package com.junwoo.nuvilab.data

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.junwoo.nuvilab.database.PageDao
import com.junwoo.nuvilab.database.PageKeyEntity
import com.junwoo.nuvilab.database.QueueDao
import com.junwoo.nuvilab.database.QueueEntity
import com.junwoo.nuvilab.database.ResultDao
import com.junwoo.nuvilab.database.ResultEntity
import com.junwoo.nuvilab.network.NetworkService
import com.junwoo.nuvilab.utils.isNetworkAvailable
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CompanyListMediator(
    private val service: NetworkService,
    private val cacheDao: ResultDao,
    private val pageDao: PageDao,
    private val queueDao: QueueDao,
    private val connectivityManager: ConnectivityManager,
) : RemoteMediator<Int, ResultEntity>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResultEntity>
    ): MediatorResult {
        val pageKey = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(true)
            LoadType.APPEND -> {
                val lastPage = pageDao.getLastPageNum() ?: 1
                lastPage + 1
            }
        }
        try {
            val response = service.getItemList(
                pageNo = pageKey.toString(),
                numOfRows = state.config.pageSize.toString()
            )
            if (loadType == LoadType.REFRESH) {
                cacheDao.deleteCacheTable()
                pageDao.clearAll()
            }
            cacheDao.addAllData(response.body.items.map { it.data.asEntity() })
            pageDao.insert(PageKeyEntity(pageNum = pageKey))
            Log.d("CPRI", "${response.body.items}")
            return MediatorResult.Success(endOfPaginationReached = response.body.items.isEmpty())
        } catch (e: IOException) {
            e.printStackTrace()
            queueDao.insert(QueueEntity(pageNum = pageKey))
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            queueDao.insert(QueueEntity(pageNum = pageKey))
            return MediatorResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }
    }


    override suspend fun initialize(): InitializeAction =
        if (connectivityManager.isNetworkAvailable()) InitializeAction.LAUNCH_INITIAL_REFRESH else InitializeAction.SKIP_INITIAL_REFRESH
}