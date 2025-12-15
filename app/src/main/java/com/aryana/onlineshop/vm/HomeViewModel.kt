package com.aryana.onlineshop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.model.product.ProductCategory
import com.aryana.onlineshop.model.site.Slider
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.product.ProductCategoryRepository
import com.aryana.onlineshop.repository.product.ProductRepository
import com.aryana.onlineshop.repository.site.SliderRepository
import com.aryana.onlineshop.config.Constant.LANG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sliderRepository: SliderRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val productRepository: ProductRepository,
) : ViewModel() {

    var slides = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
        private set

    var productCategory = MutableStateFlow<NetworkResult<List<ProductCategory>>>(NetworkResult.Loading())
        private set

    var products = MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading())
        private set

    init {
        getSliders(LANG)
        getProductCategory(LANG)
        getProduct(LANG, 0, 6)
    }

    fun loadFilterProduct(filter: Int) {
        when (filter) {
            0 -> getProduct(LANG, 0, 6)
            1 -> getProductNew(LANG)
            2 -> getProductPopular(LANG)
        }
    }

    fun getSliders(lang: String) {
        viewModelScope.launch {
            slides.value = sliderRepository.getSliders(lang)
        }
    }

    fun getProductCategory(lang: String) {
        viewModelScope.launch {
            productCategory.value = productCategoryRepository.getProductCategory(lang)
        }
    }

    fun getProduct(lang: String, pageIndex: Int, pageSize: Int) {
        viewModelScope.launch {
            products.value = productRepository.getProduct(lang, pageIndex, pageSize)
        }
    }

    fun getProductNew(lang: String) {
        viewModelScope.launch {
            products.value = productRepository.getProductNew(lang)
        }
    }

    fun getProductPopular(lang: String) {
        viewModelScope.launch {
            products.value = productRepository.getProductPopular(lang)
        }
    }

}