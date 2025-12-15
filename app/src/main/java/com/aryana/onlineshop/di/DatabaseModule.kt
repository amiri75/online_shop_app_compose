package com.aryana.onlineshop.di

import android.content.Context
import androidx.room.Room
import com.aryana.onlineshop.db.database.OnlineShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
            context,
            OnlineShopDatabase::class.java,
            "database_online_shop"
        ).build()


    @Provides
    @Singleton
    fun provideBasketDao(database: OnlineShopDatabase) = database.basketDao()

}