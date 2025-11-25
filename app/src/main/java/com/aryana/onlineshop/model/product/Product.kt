package com.aryana.onlineshop.model.product

data class Product(
    val addDate: String,
    val category: ProductCategory,
    val colors: List<ProductColor>,
    val description: String,
    val id: Long,
    val image: String,
    val lang: String,
    val price: Int,
    val sizes: List<ProductSize>,
    val title: String,
    val visitCount: Int
)