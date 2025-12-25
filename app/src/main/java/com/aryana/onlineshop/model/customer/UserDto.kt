package com.aryana.onlineshop.model.customer

data class UserDto(
    val address: String? = null,
    val customerId: Long? = null,
    val firstName: String? = null,
    val id: Long? = null,
    val lastName: String? = null,
    val oldPassword: String? = null,
    val password: String? = null,
    val phone: String? = null,
    val postalCode: String? = null,
    val repeatPassword: String? = null,
    val token: String? = null,
    val username: String? = null,
)