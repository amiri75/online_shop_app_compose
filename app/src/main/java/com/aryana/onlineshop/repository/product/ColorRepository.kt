package com.aryana.onlineshop.repository.product

import com.aryana.onlineshop.api.product.ColorApi
import com.aryana.onlineshop.model.product.ProductColor
import com.aryana.onlineshop.network.BaseApiResponse
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class ColorRepository @Inject constructor(
    private val api: ColorApi,
) {

    suspend fun getColor(lang: String): NetworkResult<List<ProductColor>> =
        BaseApiResponse.safeApiCall {
            api.getColor(lang)
        }

    suspend fun getColorById(id : Long): NetworkResult<List<ProductColor>> =
        BaseApiResponse.safeApiCall {
            api.getColorById(id)
        }


}