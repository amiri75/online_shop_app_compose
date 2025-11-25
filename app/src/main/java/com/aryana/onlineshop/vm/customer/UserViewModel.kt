package com.aryana.onlineshop.vm.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.customer.User
import com.aryana.onlineshop.model.customer.UserDto
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.customer.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {

    private val _userInfo = MutableStateFlow<NetworkResult<User>>(NetworkResult.Loading())
    val userInfo: StateFlow<NetworkResult<User>> = _userInfo

    private val _changePassword = MutableStateFlow<NetworkResult<User>>(NetworkResult.Loading())
    val changePassword: StateFlow<NetworkResult<User>> = _changePassword

    private val _login = MutableStateFlow<NetworkResult<UserDto>>(NetworkResult.Loading())
    val login: StateFlow<NetworkResult<UserDto>> = _login

    private val _register = MutableStateFlow<NetworkResult<User>>(NetworkResult.Loading())
    val register: StateFlow<NetworkResult<User>> = _register

    private val _update = MutableStateFlow<NetworkResult<User>>(NetworkResult.Loading())
    val update: StateFlow<NetworkResult<User>> = _update

    fun getUserInfo(token: String) {
        viewModelScope.launch {
            _userInfo.value = repository.getUserInfo(token)
        }
    }

    fun changePassword(user: UserDto, token: String) {
        viewModelScope.launch {
            _changePassword.value = repository.changePassword(user, token)
        }
    }

    fun login(user: UserDto) {
        viewModelScope.launch {
            _login.value = repository.login(user)
        }
    }

    fun register(user: UserDto) {
        viewModelScope.launch {
            _register.value = repository.register(user)
        }
    }

    fun update(user: UserDto, token: String) {
        viewModelScope.launch {
            _update.value = repository.update(user, token)
        }
    }
}