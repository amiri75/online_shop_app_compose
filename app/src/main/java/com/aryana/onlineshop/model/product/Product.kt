package com.aryana.onlineshop.model.product

data class Product(
    val addDate: String? = null,
    val category: ProductCategory? = null,
    val colors: List<ProductColor>? = null,
    val description: String? = null,
    val id: Long? = null,
    val image: String? = null,
    val lang: String? = null,
    val price: Int? = null,
    val sizes: List<ProductSize>? = null,
    val title: String? = null,
    val visitCount: Int? = null
)