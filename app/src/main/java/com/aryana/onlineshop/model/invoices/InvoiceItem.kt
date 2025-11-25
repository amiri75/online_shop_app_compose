package com.aryana.onlineshop.model.invoices

import com.aryana.onlineshop.model.products.Product

data class InvoiceItem(
    val id: Long,
    val product: Product,
    val quantity: Int,
    val unitPrice: Long
)