package com.aryana.onlineshop.repository.site

import com.aryana.onlineshop.api.site.ContentApi
import com.aryana.onlineshop.model.site.Content
import com.aryana.onlineshop.network.BaseApiResponse.safeApiCall
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val api: ContentApi,
) {

    suspend fun getContent(): NetworkResult<List<Content>> =
        safeApiCall {
            api.getContent()
        }

    suspend fun getContentById(title: String): NetworkResult<List<Content>> =
        safeApiCall {
            api.getContentById(title)
        }
}