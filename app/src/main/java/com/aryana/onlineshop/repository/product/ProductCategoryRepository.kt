package com.aryana.onlineshop.repository.product

import com.aryana.onlineshop.api.product.ProductCategoryApi
import com.aryana.onlineshop.model.product.ProductCategory
import com.aryana.onlineshop.network.BaseApiResponse
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class ProductCategoryRepository @Inject constructor(
    private val api: ProductCategoryApi,
) {

    suspend fun getProductCategory(lang: String): NetworkResult<List<ProductCategory>> =
        BaseApiResponse.safeApiCall {
            api.getProductCategory(lang)
        }

    suspend fun getProductCategoryById(id: Long): NetworkResult<List<ProductCategory>> =
        BaseApiResponse.safeApiCall {
            api.getProductCategoryById(id)
        }


}