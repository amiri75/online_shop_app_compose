package com.aryana.onlineshop.repository.product

import com.aryana.onlineshop.api.product.ProductApi
import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.network.BaseApiResponse
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApi,
) {

    suspend fun getProduct(lang: String, pageIndex: Int, pageSize: Int): NetworkResult<List<Product>> =
        BaseApiResponse.safeApiCall {
            api.getProduct(lang, pageIndex, pageSize)
        }

    suspend fun getProductNew(lang: String): NetworkResult<List<Product>> =
        BaseApiResponse.safeApiCall {
            api.getProductNew(lang)
        }

    suspend fun getProductPopular(lang: String): NetworkResult<List<Product>> =
        BaseApiResponse.safeApiCall {
            api.getProductPopular(lang)
        }

    suspend fun getProductById(id: Long): NetworkResult<List<Product>> =
        BaseApiResponse.safeApiCall {
            api.getProductById(id)
        }

    suspend fun getProductCatById(id: Long, pageIndex: Int, pageSize: Int): NetworkResult<List<Product>> =
        BaseApiResponse.safeApiCall {
            api.getProductCatById(id, pageIndex, pageSize)
        }
}