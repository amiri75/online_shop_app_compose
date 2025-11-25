package com.aryana.onlineshop.api.invoice

import com.aryana.onlineshop.model.invoice.PaymentTransaction
import com.aryana.onlineshop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {

    @POST("trx/gotoPayment")
    suspend fun gotoPayment(
        @Body data: PaymentTransaction,
    ): Response<ApiResponse<List<String>>>

}