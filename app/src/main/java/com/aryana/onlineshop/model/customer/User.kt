package com.aryana.onlineshop.model.customer

data class User(
    val customer: Customer,
    val id: Long,
    val password: String,
    val username: String
)