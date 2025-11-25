package com.aryana.onlineshop.model.invoices

import com.aryana.onlineshop.model.customer.UserDto

data class PaymentTransaction(
    val items : List<InvoiceItem>,
    val user : UserDto
)
