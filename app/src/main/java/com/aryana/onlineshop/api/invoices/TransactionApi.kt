package com.aryana.onlineshop.api.invoices

import com.aryana.onlineshop.model.invoices.PaymentTransaction
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