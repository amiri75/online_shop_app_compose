package com.aryana.onlineshop.vm.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.product.ProductCategory
import com.aryana.onlineshop.model.product.ProductColor
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.product.ColorRepository
import com.aryana.onlineshop.repository.product.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(
    private val repository: ColorRepository,
) : ViewModel() {

    private val _colorProduct = MutableStateFlow<NetworkResult<List<ProductColor>>>(NetworkResult.Loading())
    val colorProduct: StateFlow<NetworkResult<List<ProductColor>>> = _colorProduct

    private val _colorProductById = MutableStateFlow<NetworkResult<List<ProductColor>>>(NetworkResult.Loading())
    val colorProductById: StateFlow<NetworkResult<List<ProductColor>>> = _colorProductById

    fun getColor(lang: String) {
        viewModelScope.launch {
            _colorProduct.value  = repository.getColor(lang)
        }
    }

    fun getColorById(id: Long) {
        viewModelScope.launch {
            _colorProductById.value  = repository.getColorById(id)
        }
    }

}