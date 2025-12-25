package com.aryana.onlineshop.model.invoice.mapper

import com.aryana.onlineshop.db.entity.BasketEntity
import com.aryana.onlineshop.model.invoice.InvoiceItem
import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.model.product.ProductColor
import com.aryana.onlineshop.model.product.ProductSize

fun BasketEntity.toInvoiceItem(): InvoiceItem {
    return InvoiceItem(
        product = Product(
            id = this.productId,
            colors = listOf(ProductColor(id = this.colorId)),
            sizes = listOf(ProductSize(id = this.sizeId)),
        ),
        quantity = this.quantity,
    )
}