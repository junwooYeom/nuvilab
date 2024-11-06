package com.junwoo.nuvilab.domain

interface OperationRepository {
    suspend fun getPendingOperations(maxRetries: Int): List<FailedQueue>

    suspend fun retryOperation(operation: FailedQueue)

    suspend fun markOperationAsComplete(operation: FailedQueue)

    suspend fun incrementRetryCount(operation: FailedQueue)
}