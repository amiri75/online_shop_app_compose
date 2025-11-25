package com.aryana.onlineshop.api.product

import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("product")
    suspend fun getProduct(
        @Query("lang") lang: String,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<ApiResponse<List<Product>>>

    @GET("product/new")
    suspend fun getProductNew(@Query("lang") lang: String): Response<ApiResponse<List<Product>>>

    @GET("product/popular")
    suspend fun getProductPopular(@Query("lang") lang: String): Response<ApiResponse<List<Product>>>

    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: Long): Response<ApiResponse<List<Product>>>

    @GET("product/cat/{id}")
    suspend fun getProductCatById(
        @Path("id") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<ApiResponse<List<Product>>>
}