package com.aryana.onlineshop.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.model.product.ProductColor
import com.aryana.onlineshop.model.product.ProductSize
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var id: Long = savedStateHandle["id"] ?: 0
        private set
    var product = MutableStateFlow<Product?>(null)
        private set

    var loading = MutableStateFlow(false)


    var selectedSize by mutableStateOf<ProductSize?>(null)
    var selectedColor by mutableStateOf<ProductColor?>(null)


    init {
        getProductById(id)
    }

    fun getProductById(id: Long) {
        viewModelScope.launch {
            when (val result = productRepository.getProductById(id)) {
                is NetworkResult.Success -> {
                    product.value = result.data?.firstOrNull { it.id == id }
                    product.value?.sizes?.firstOrNull().let {
                        selectedSize = it
                    }
                    product.value?.colors?.firstOrNull().let {
                        selectedColor = it
                    }
                    loading.value = false
                }

                is NetworkResult.Error -> {
                    loading.value = false
                }

                is NetworkResult.Loading -> {
                    loading.value = true
                }
            }
        }
    }
}
