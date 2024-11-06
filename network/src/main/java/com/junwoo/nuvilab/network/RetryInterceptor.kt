package com.junwoo.nuvilab.network

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.math.pow

class RetryInterceptor(
    private val maxRetries: Int = 5,
    private val initialDelay: Long = 1000,
    private val maxDelay: Long = 60000
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking { retry(chain) }

    private suspend fun retry(chain: Interceptor.Chain): Response {
        Log.d("CPRI", "${chain.request().url}")
        var attempt = 0
        while(attempt < maxRetries) {
            try {
                return chain.proceed(chain.request())
            } catch (e: IOException) {
                attempt++
                if (attempt >= maxRetries) throw e

                delay(minOf((initialDelay * 2.0.pow(attempt).toLong()), maxDelay))
            }
        }
        throw IOException("Maximum retry attempts reached.")
    }
}