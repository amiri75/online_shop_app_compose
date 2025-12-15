package com.aryana.onlineshop.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val catId: Long = savedStateHandle["catId"] ?: 0
    var title: String = savedStateHandle["title"] ?: ""
        private set


    var products = MutableStateFlow<List<Product>>(emptyList())
        private set

    var isLoading = MutableStateFlow(false)
        private set

    private var pageIndex = 0

    private val pageSize = 4

    private var endReached = false

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading.value || endReached) return

        viewModelScope.launch {
            isLoading.value = true
            val result = productRepository.getProductCatById(catId, pageIndex, pageSize)
            when (result) {
                is NetworkResult.Success -> {
                    val newData = result.data ?: emptyList()
                    // اضافه کردن داده‌های جدید به لیست قبلی
                    val current = products.value.toMutableList()
                    current.addAll(newData)
                    products.value = current
                    // اگر تعداد کمتر از pageSize بود یعنی صفحه آخر
                    if (newData.size < pageSize) {
                        endReached = true
                    } else {
                        pageIndex++ // صفحه بعد
                    }
                }
                is NetworkResult.Loading -> {}
                is NetworkResult.Error -> {}
            }
            isLoading.value = false
        }
    }

}