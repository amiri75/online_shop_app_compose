package com.aryana.onlineshop.api.site

import com.aryana.onlineshop.model.site.Blog
import com.aryana.onlineshop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogApi {

    @GET("blog")
    suspend fun getBlog(): Response<ApiResponse<List<Blog>>>

    @GET("blog/{id}")
    suspend fun getBlogById(@Path("id")id: Long): Response<ApiResponse<List<Blog>>>
}