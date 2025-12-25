package com.aryana.onlineshop.api.invoice

import com.aryana.onlineshop.model.invoice.Invoice
import com.aryana.onlineshop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceApi {

    @GET("invoice/{id}")
    suspend fun getInvoiceById(
        @Path("id") id: Long,
        @Header("Authorization") token : String
    ): Response<ApiResponse<List<Invoice>>>


    @GET("invoice/user/{userId}")
    suspend fun getInvoiceByUserId(
        @Path("userId") userId: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
        @Header("Authorization") token : String
    ): Response<ApiResponse<List<Invoice>>>
}