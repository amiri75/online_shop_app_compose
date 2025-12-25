package com.aryana.onlineshop.vm

import android.util.Log.v
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.customer.LoginRequestDto
import com.aryana.onlineshop.model.customer.User
import com.aryana.onlineshop.model.customer.UserDto
import com.aryana.onlineshop.model.customer.mapper.toUserEntity
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.customer.UserEntityRepository
import com.aryana.onlineshop.repository.customer.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userEntityRepository: UserEntityRepository,
) : ViewModel() {


    val currentUser = userEntityRepository.getCurrentUser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    fun login(
        username: String,
        password: String,
        onLoading: () -> Unit = {},
        onSuccess: (String?) -> Unit = {},
        onError: (String) -> Unit = {},
    ) {
        val userDto = LoginRequestDto(username = username, password = password)
        viewModelScope.launch {
            when (val userData = userRepository.login(userDto)) {
                is NetworkResult.Success -> {
                    if (userData.data != null) {
                        val user = userData.data[0].firstName
                        withContext(Dispatchers.IO) {
                            userEntityRepository.insert(userData.data[0].toUserEntity())
                        }
                        onSuccess(user)
                    }else{
                        onSuccess("")
                    }
                }

                is NetworkResult.Error -> {
                    if (userData.message != null) {
                        onError(userData.message)
                    }

                }

                is NetworkResult.Loading -> {
                    onLoading()
                }
            }
        }
    }

    fun changePassword(
        oldPassword: String,
        newPassword: String,
        repeatPassword: String,
        onSuccess: (String?, List<User>?) -> Unit,
        onLoading: () -> Unit,
        onError: (String?) -> Unit
    ) {
        val user = UserDto(
            firstName = currentUser.value?.firstName,
            lastName = currentUser.value?.lastName,
            address = currentUser.value?.address,
            phone = currentUser.value?.phone,
            postalCode = currentUser.value?.postalCode,
            customerId = currentUser.value?.customerId,
            username = currentUser.value?.username,
            id = currentUser.value?.userId,
            oldPassword = oldPassword,
            password = newPassword,
            repeatPassword = repeatPassword
        )
        viewModelScope.launch {
            val user = userRepository.changePassword(user, currentUser.value?.token ?: "")
            when (user) {
                is NetworkResult.Success-> {
                    onSuccess(user.message,user.data)
                }
                is NetworkResult.Loading -> {
                    onLoading()
                }
                is NetworkResult.Error -> {
                    onError(user.message)
                }
            }
        }
    }
}