package com.aryana.onlineshop.di

import com.aryana.onlineshop.api.customer.UserApi
import com.aryana.onlineshop.api.invoice.InvoiceApi
import com.aryana.onlineshop.api.invoice.TransactionApi
import com.aryana.onlineshop.api.product.ColorApi
import com.aryana.onlineshop.api.product.ProductApi
import com.aryana.onlineshop.api.product.ProductCategoryApi
import com.aryana.onlineshop.api.site.BlogApi
import com.aryana.onlineshop.api.site.ContentApi
import com.aryana.onlineshop.api.site.SliderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://onlineshop.holosen.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideSliderApi(retrofit: Retrofit): SliderApi =
        retrofit.create(SliderApi::class.java)

    @Provides
    @Singleton
    fun provideBlogApi(retrofit: Retrofit): BlogApi =
        retrofit.create(BlogApi::class.java)

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi =
        retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideInvoiceApi(retrofit: Retrofit): InvoiceApi =
        retrofit.create(InvoiceApi::class.java)

    @Provides
    @Singleton
    fun provideTransactionApi(retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)

    @Provides
    @Singleton
    fun provideColorApi(retrofit: Retrofit): ColorApi =
        retrofit.create(ColorApi::class.java)

    @Provides
    @Singleton
    fun provideProductCategoryApi(retrofit: Retrofit): ProductCategoryApi =
        retrofit.create(ProductCategoryApi::class.java)

    @Provides
    @Singleton
    fun provideContentApi(retrofit: Retrofit): ContentApi =
        retrofit.create(ContentApi::class.java)


}