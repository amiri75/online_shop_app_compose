package com.aryana.onlineshop.api.site

import com.aryana.onlineshop.model.site.Content
import com.aryana.onlineshop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {

    @GET("content")
    suspend fun getContent(): Response<ApiResponse<List<Content>>>

    @GET("content/{title}")
    suspend fun getContentById(@Path("title") title: String): Response<ApiResponse<List<Content>>>
}