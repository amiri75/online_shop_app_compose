package com.aryana.onlineshop.model.customer

data class UserDto(
    val address: String,
    val customerId: Long,
    val firstName: String,
    val id: Long,
    val lastName: String,
    val oldPassword: String,
    val password: String,
    val phone: String,
    val postalCode: String,
    val repeatPassword: String,
    val token: String,
    val username: String
)