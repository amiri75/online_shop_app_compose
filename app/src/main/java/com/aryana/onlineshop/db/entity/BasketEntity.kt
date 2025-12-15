package com.aryana.onlineshop.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class BasketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val productId: Long,
    val quantity: Int,
    val colorId: Long,
    val sizeId: Long,
    val title: String?,
    val image: String?,
    val price: Int,
    val colorHex: String?,
    val size : String?
    )
