package com.aryana.onlineshop.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aryana.onlineshop.db.dao.BasketDao
import com.aryana.onlineshop.db.dao.UserDao
import com.aryana.onlineshop.db.entity.BasketEntity
import com.aryana.onlineshop.db.entity.UserEntity

@Database(entities = [BasketEntity::class,UserEntity::class], version = 1, exportSchema = false)
abstract class OnlineShopDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketDao
    abstract fun userDao(): UserDao
}