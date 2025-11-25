package com.aryana.onlineshop.repository.invoice

import com.aryana.onlineshop.api.invoice.TransactionApi
import com.aryana.onlineshop.model.invoice.PaymentTransaction
import com.aryana.onlineshop.network.BaseApiResponse
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val api: TransactionApi,
) {

    suspend fun gotoPayment(data: PaymentTransaction): NetworkResult<List<String>> =
        BaseApiResponse.safeApiCall {
            api.gotoPayment(data)
        }
}