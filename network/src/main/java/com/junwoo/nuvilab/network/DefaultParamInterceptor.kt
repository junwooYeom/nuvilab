package com.junwoo.nuvilab.network

import okhttp3.Interceptor
import okhttp3.Response

class DefaultParamInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return chain.proceed(
            request.newBuilder().url(
                request.url.newBuilder().addQueryParameter(
                    "ServiceKey",
                    "dZRAmmaQodL5JGVt2F6HT5I6Qq5CPLvlXXIbx76e/scL0p/EGkYGdDWz+rQimELB5B3LoHhO+b90kskdvvm2EQ=="
                ).build()
            ).build()
        )
    }
}