package com.aryana.onlineshop.api.site

import com.aryana.onlineshop.model.site.Slider
import com.aryana.onlineshop.network.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SliderApi {

    @GET("slider")
    suspend fun getSlider(@Query("lang") lang: String): Response<ApiResponse<List<Slider>>>

    @GET("slider/{id}")
    suspend fun getSliderById(@Path("id") id: Long): Response<ApiResponse<List<Slider>>>
}