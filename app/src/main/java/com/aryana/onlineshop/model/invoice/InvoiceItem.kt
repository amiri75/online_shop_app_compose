package com.aryana.onlineshop.model.invoice

import com.aryana.onlineshop.model.product.Product

data class InvoiceItem(
    val id: Long,
    val product: Product,
    val quantity: Int,
    val unitPrice: Long
)