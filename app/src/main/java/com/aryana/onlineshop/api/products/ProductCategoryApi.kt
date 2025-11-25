package com.aryana.onlineshop.api.products

import com.aryana.onlineshop.model.products.ProductCategory
import com.aryana.onlineshop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductCategoryApi {

    @GET("productCategory")
    suspend fun getProductCategory(
        @Query("lang") lang: String,
    ): Response<ApiResponse<List<ProductCategory>>>

    @GET("productCategory/{id}")
    suspend fun getProductCategoryById(@Path("id") id: Long): Response<ApiResponse<List<ProductCategory>>>
}