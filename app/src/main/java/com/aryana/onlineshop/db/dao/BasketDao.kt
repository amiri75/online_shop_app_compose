package com.aryana.onlineshop.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aryana.onlineshop.db.entity.BasketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Insert
    suspend fun insertBasket(basketEntity: BasketEntity)

    @Update
    suspend fun update(basketEntity: BasketEntity)

    @Delete
    suspend fun delete(basketEntity: BasketEntity)

    @Query("SELECT * FROM basket")
    fun getAllBasket(): Flow<List<BasketEntity>>

    @Query("SELECT * FROM basket WHERE productId = :productId AND colorId =:colorId AND sizeId =:sizeId  LIMIT 1")
    suspend fun findItem(productId: Long, colorId: Long, sizeId: Long): BasketEntity?

    @Query("DELETE FROM basket")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM basket")
    fun getCount(): Flow<Int>

    @Query("UPDATE basket SET quantity = quantity + 1 WHERE productId = :productId")
    suspend fun increaseQuantity(productId: Long)

    @Query("UPDATE basket SET quantity = quantity - 1  WHERE productId = :productId AND quantity > 1")
    suspend fun decreaseQuantity(productId: Long)
}