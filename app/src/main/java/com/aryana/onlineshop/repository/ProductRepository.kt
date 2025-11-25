package com.aryana.onlineshop.repository

import com.aryana.onlineshop.api.products.ProductApi
import com.aryana.onlineshop.model.products.Product
import com.aryana.onlineshop.network.ApiResponse
import com.aryana.onlineshop.network.BaseApiResponse.safeApiCall
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApi,

    ) {

    suspend fun getProduct(lang: String, pageIndex: Int, pageSize: Int): NetworkResult<List<Product>> =
        safeApiCall {
            api.getProduct(lang, pageIndex, pageSize)
        }


}