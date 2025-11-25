package com.aryana.onlineshop.vm.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.product.ProductCategory
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.product.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(
    private val repository: ProductCategoryRepository,
) : ViewModel() {

    private val _productCategory = MutableStateFlow<NetworkResult<List<ProductCategory>>>(NetworkResult.Loading())
    val productCategory: StateFlow<NetworkResult<List<ProductCategory>>> = _productCategory

    private val _productCategoryById = MutableStateFlow<NetworkResult<List<ProductCategory>>>(NetworkResult.Loading())
    val productCategoryById: StateFlow<NetworkResult<List<ProductCategory>>> = _productCategoryById

    fun getProductCategory(lang: String) {
        viewModelScope.launch {
            _productCategory.value  = repository.getProductCategory(lang)
        }
    }

    fun getProductCategoryById(id: Long) {
        viewModelScope.launch {
            _productCategoryById.value  = repository.getProductCategoryById(id)
        }
    }

}