package com.aryana.onlineshop.vm.invoice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.invoice.PaymentTransaction
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.invoice.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {


    private val _gotoPayment = MutableStateFlow<NetworkResult<List<String>>>(NetworkResult.Loading())
    val gotoPayment: StateFlow<NetworkResult<List<String>>> = _gotoPayment


    fun gotoPayment(data: PaymentTransaction) {
        viewModelScope.launch {
            _gotoPayment.value = repository.gotoPayment(data)
        }
    }


}