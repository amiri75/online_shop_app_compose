package com.aryana.onlineshop.model.invoices

import com.aryana.onlineshop.model.customer.User

data class Invoice(
    val addDate: String,
    val id: Long,
    val items: List<InvoiceItem>,
    val paymentDate: String,
    val status: String,
    val user: User
)