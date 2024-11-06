package com.junwoo.nuvilab.network

import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("getCertCompanyListService2")
    suspend fun getItemList(
        @Query("returnType") returnType: String = "json",
        @Query("pageNo") pageNo: String,
        @Query("numOfRows") numOfRows: String = "10"
    ): BaseResponse<PagedResponse<ResultDto>>
}