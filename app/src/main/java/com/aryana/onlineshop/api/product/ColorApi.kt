package com.aryana.onlineshop.api.product

import com.aryana.onlineshop.model.product.ProductColor
import com.aryana.onlineshop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ColorApi {

    @GET("color")
    suspend fun getColor(
        @Query("lang") lang: String,
    ): Response<ApiResponse<List<ProductColor>>>

    @GET("color/{id}")
    suspend fun getColorById(@Path("id") id: Long): Response<ApiResponse<List<ProductColor>>>
}