package com.junwoo.nuvilab.domain.operation

import com.junwoo.nuvilab.domain.OperationRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import javax.inject.Inject

class RetryOperationUseCase @Inject constructor(
    private val repository: OperationRepository
) {

    suspend operator fun invoke(maxRetries: Int) = coroutineScope {
        val pendingOperation = repository.getPendingOperations(maxRetries)
        pendingOperation.map {
            async {
                try {
                    repository.retryOperation(it)
                    repository.markOperationAsComplete(it)
                } catch (e: Exception) {
                    repository.incrementRetryCount(it)
                }
            }
        }.awaitAll()
    }
}