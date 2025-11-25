package com.aryana.onlineshop.repository.site

import com.aryana.onlineshop.api.site.BlogApi
import com.aryana.onlineshop.model.site.Blog
import com.aryana.onlineshop.network.BaseApiResponse.safeApiCall
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class BlogRepository @Inject constructor(
    private val api: BlogApi,
) {

    suspend fun getBlogs(): NetworkResult<List<Blog>> =
        safeApiCall {
            api.getBlog()
        }

    suspend fun getBlogsById(id: Long): NetworkResult<List<Blog>> =
        safeApiCall {
            api.getBlogById(id)
        }

}