package com.junwoo.nuvilab.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.junwoo.nuvilab.domain.operation.RetryOperationUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class RetryWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    private val useCase: RetryOperationUseCase,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams){
    override suspend fun doWork(): Result {
        return try {
            useCase(maxRetries = 5)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}