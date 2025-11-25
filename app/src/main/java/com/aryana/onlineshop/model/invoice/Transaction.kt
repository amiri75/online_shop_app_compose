package com.aryana.onlineshop.model.invoice

import com.aryana.onlineshop.model.customer.UserDto

data class Transaction(
    val items: List<InvoiceItem>,
    val user: UserDto
)