package com.aryana.onlineshop.repository

import com.aryana.onlineshop.api.site.SliderApi
import com.aryana.onlineshop.model.site.Slider
import com.aryana.onlineshop.network.BaseApiResponse.safeApiCall
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class SliderRepository @Inject constructor(
    private val api: SliderApi,
) {

    suspend fun getSliders(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider("fa")
        }

    suspend fun getSlidersById(id : Int): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSliderById(id)
        }

}