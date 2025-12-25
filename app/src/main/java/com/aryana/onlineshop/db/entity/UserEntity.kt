package com.aryana.onlineshop.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Long? = 0,
    val address: String?,
    val customerId: Long?,
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val postalCode: String?,
    val token: String? ,
    val username: String?
)
