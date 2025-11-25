package com.aryana.onlineshop.model.site

data class Blog(
    val addDate: String,
    val description: String,
    val id: Long,
    val image: String,
    val subTitle: String,
    val title: String,
    val visitCount: Int
)