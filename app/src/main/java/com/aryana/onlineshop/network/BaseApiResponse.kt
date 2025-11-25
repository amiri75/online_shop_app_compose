package com.aryana.onlineshop.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

// صدا زدن API به صورت امن
object BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<ApiResponse<T>>): NetworkResult<T> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        return@withContext NetworkResult.Success(body.message, body.data)
                    }
                }
                return@withContext error("code : ${response.code()} , message : ${response.message()}")
            } catch (e: Exception) {
                return@withContext error(e.message ?: e.toString())
            }
        }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("api call failed : $errorMessage ")


    fun prepareToken(token: String): String {
        var result = token
        if (!token.lowercase().startsWith("bearer"))
            result = "Bearer $token"
        return result
    }

}


