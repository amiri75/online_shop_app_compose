package com.aryana.onlineshop.repository.customer

import com.aryana.onlineshop.api.customer.UserApi
import com.aryana.onlineshop.model.customer.User
import com.aryana.onlineshop.model.customer.UserDto
import com.aryana.onlineshop.network.BaseApiResponse
import com.aryana.onlineshop.network.BaseApiResponse.prepareToken
import com.aryana.onlineshop.network.NetworkResult
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi,
) {
    suspend fun getUserInfo(token: String): NetworkResult<User> =
        BaseApiResponse.safeApiCall {
            api.getUserInfo(prepareToken(token))
        }

    suspend fun changePassword(user: UserDto, token: String): NetworkResult<User> =
        BaseApiResponse.safeApiCall {
            api.changePassword(user, prepareToken(token))
        }

    suspend fun login(user: UserDto): NetworkResult<UserDto> =
        BaseApiResponse.safeApiCall {
            api.login(user)
        }

    suspend fun register(user: UserDto): NetworkResult<User> =
        BaseApiResponse.safeApiCall {
            api.register(user)
        }

    suspend fun update(user: UserDto, token: String): NetworkResult<User> =
        BaseApiResponse.safeApiCall {
            api.update(user, prepareToken(token))
        }
}