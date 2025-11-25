package com.aryana.onlineshop.model.customer

data class Customer(
    val address: String,
    val firstName: String,
    val id: Long,
    val lastName: String,
    val phone: String,
    val postalCode: String
)