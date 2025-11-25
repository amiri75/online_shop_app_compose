package com.aryana.onlineshop.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.products.Product
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel(){

    private val _product = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading())
    val product = _product

     fun getProduct(lang: String, pageIndex: Int, pageSize: Int){
         viewModelScope.launch {
             _product.value = repository.getProduct(lang, pageIndex, pageSize)
         }
     }

}