package com.aryana.onlineshop.repository.invoice

import com.aryana.onlineshop.api.invoice.InvoiceApi
import com.aryana.onlineshop.model.invoice.Invoice
import com.aryana.onlineshop.network.BaseApiResponse
import com.aryana.onlineshop.network.BaseApiResponse.prepareToken
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class InvoiceRepository @Inject constructor(
    private val api: InvoiceApi,
) {

    suspend fun getInvoiceById(id: Long, token: String): NetworkResult<List<Invoice>> =
        BaseApiResponse.safeApiCall {
            api.getInvoiceById(id, prepareToken(token))
        }

    suspend fun getInvoiceByUserId(userId: Long,pageIndex: Int, pageSize: Int,  token: String): NetworkResult<List<Invoice>> =
        BaseApiResponse.safeApiCall {
            api.getInvoiceByUserId(userId,pageIndex, pageSize,prepareToken(token))
        }
}