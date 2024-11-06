package com.junwoo.nuvilab.data

import com.junwoo.nuvilab.database.QueueDao
import com.junwoo.nuvilab.database.ResultDao
import com.junwoo.nuvilab.domain.FailedQueue
import com.junwoo.nuvilab.domain.OperationRepository
import com.junwoo.nuvilab.network.NetworkService
import javax.inject.Inject

class OperationRepositoryImpl @Inject constructor(
    private val service: NetworkService,
    private val queueDao: QueueDao,
    private val resultDao: ResultDao,
) : OperationRepository {
    override suspend fun getPendingOperations(maxRetries: Int): List<FailedQueue> =
        queueDao.getPendingOperations(maxRetries).map { it.asDomainModel() }

    override suspend fun retryOperation(operation: FailedQueue) {
        val response = service.getItemList(pageNo = "${operation.pageNum}", numOfRows = "10")
        resultDao.addAllData(response.body.items.map { it.data.asEntity() })
    }

    override suspend fun markOperationAsComplete(operation: FailedQueue) {
        queueDao.deleteOperation(operation.id)
    }

    override suspend fun incrementRetryCount(operation: FailedQueue) {
        queueDao.insert(operation.copy(retryCount = operation.retryCount + 1).toEntity())
    }
}