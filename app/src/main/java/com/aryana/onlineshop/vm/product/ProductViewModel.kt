package com.aryana.onlineshop.vm.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
) : ViewModel() {

    private val _product = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading())
    val product: StateFlow<NetworkResult<List<Product>>> = _product

    private val _productNew = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading())
    val productNew: StateFlow<NetworkResult<List<Product>>> = _productNew

    private val _productPopular = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading())
    val productPopular: StateFlow<NetworkResult<List<Product>>> = _productPopular

    private val _productById = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading())
    val productById: StateFlow<NetworkResult<List<Product>>> = _productById

    private val _productCatById = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading())
    val productCatById: StateFlow<NetworkResult<List<Product>>> = _productCatById


    fun getProduct(lang: String, pageIndex: Int, pageSize: Int) {
        viewModelScope.launch {
            _product.value = repository.getProduct(lang, pageIndex, pageSize)
        }
    }

    fun getProductNew(lang: String) {
        viewModelScope.launch {
            _productNew.value = repository.getProductNew(lang)
        }
    }

    fun getProductPopular(lang: String) {
        viewModelScope.launch {
            _productPopular.value = repository.getProductPopular(lang)
        }
    }

    fun getProductById(id: Long) {
        viewModelScope.launch {
            _productById.value = repository.getProductById(id)
        }
    }

    fun getProductCatById(id: Long, pageIndex: Int, pageSize: Int) {
        viewModelScope.launch {
            _productCatById.value = repository.getProductCatById(id, pageIndex, pageSize)
        }
    }

}