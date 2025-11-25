package com.aryana.onlineshop.network

data class ApiResponse<T>(
    val data: T,
    val message: String,
    val status: String,
    val totalCount: Int
)