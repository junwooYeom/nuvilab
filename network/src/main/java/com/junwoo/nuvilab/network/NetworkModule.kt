package com.junwoo.nuvilab.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder()
            .addInterceptor(DefaultParamInterceptor())
            .addNetworkInterceptor(RetryInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build())
        .baseUrl("https://apis.data.go.kr/B553748/CertCompanyListService2/")
        .addConverterFactory(Json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
        .build()
}