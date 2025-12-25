package com.aryana.onlineshop.repository.basket

import com.aryana.onlineshop.db.dao.BasketDao
import com.aryana.onlineshop.db.entity.BasketEntity
import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.model.product.ProductColor
import com.aryana.onlineshop.model.product.ProductSize
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val dao: BasketDao,
) {
    val getCount = dao.getCount()
    fun getAllBasket(): Flow<List<BasketEntity>> =
        dao.getAllBasket()

    suspend fun deleteItem(basketEntity: BasketEntity) =
        dao.delete(basketEntity)

    suspend fun increaseQuantity(productId: Long, colorId: Long, sizeId: Long) =
        dao.increaseQuantity(productId, colorId, sizeId)

    suspend fun decreaseQuantity(productId: Long, colorId: Long, sizeId: Long) =
        dao.decreaseQuantity(productId, colorId, sizeId)

    suspend fun clear() =
        dao.clear()


    suspend fun addToBasket(product: Product?, color: ProductColor?, size: ProductSize?) {
        val oldItem = dao.findItem(
            productId = product?.id ?: 0,
            colorId = color?.id ?: 0,
            sizeId = size?.id ?: 0
        )

        if (oldItem == null) {
            // ردیف جدید
            dao.insertBasket(
                BasketEntity(
                    productId = product?.id ?: 0,
                    title = product?.title,
                    image = product?.image,
                    price = product?.price ?: 0,
                    quantity = 1,
                    colorId = color?.id ?: 0,
                    sizeId = size?.id ?: 0,
                    colorHex = color?.hexValue ,
                    size = size?.title
                )
            )
        } else {
            // آپدیت ردیف موجود
            dao.update(oldItem.copy(quantity = oldItem.quantity + 1))
        }
    }


}