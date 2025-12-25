package com.aryana.onlineshop.model.customer.mapper

import com.aryana.onlineshop.db.entity.UserEntity
import com.aryana.onlineshop.model.customer.UserDto

fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        username = this.username,
        firstName = this.firstName,
        lastName = this.lastName,
        address = this.address,
        phone = this.phone,
        postalCode = this.postalCode,
        customerId = this.customerId,
        token = this.token,
        userId = this.id
    )


}