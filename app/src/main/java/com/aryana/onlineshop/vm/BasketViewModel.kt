package com.aryana.onlineshop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.db.entity.BasketEntity
import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.model.product.ProductColor
import com.aryana.onlineshop.model.product.ProductSize
import com.aryana.onlineshop.repository.basket.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: BasketRepository,
) : ViewModel() {

    val getAllBasket = repository.getAllBasket().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val getCountBasket = repository.getCount.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = 0
    )

    fun deleteItem(basketEntity: BasketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(basketEntity)
        }
    }

    fun increaseQuantity(productId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.increaseQuantity(productId)
        }
    }

    fun decreaseQuantity(productId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.decreaseQuantity(productId)
        }
    }



    fun addToBasket(product: Product?, color: ProductColor?, size: ProductSize?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToBasket(product, color, size)
        }
    }

}