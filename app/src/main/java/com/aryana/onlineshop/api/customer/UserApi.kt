package com.aryana.onlineshop.api.customer

import com.aryana.onlineshop.model.customer.User
import com.aryana.onlineshop.model.customer.UserDto
import com.aryana.onlineshop.network.NetworkResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApi {

    @GET("user")
    suspend fun getUserInfo(
        @Header("Authorization") token : String
    ): Response<NetworkResult<User>>

    @PUT("user/changePassword")
    suspend fun changePassword(
        @Body user : UserDto,
        @Header("Authorization") token : String
    ): Response<NetworkResult<User>>

    @POST("user/login")
    suspend fun login(
        @Body user : UserDto,
    ): Response<NetworkResult<UserDto>>

    @POST("user/register")
    suspend fun register(
        @Body user : UserDto,
    ): Response<NetworkResult<User>>

    @PUT("user/update")
    suspend fun update(
        @Body user : UserDto,
        @Header("Authorization") token : String
    ): Response<NetworkResult<User>>
}