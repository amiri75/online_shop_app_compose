package com.aryana.onlineshop.vm.invoice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.customer.UserDto
import com.aryana.onlineshop.model.invoice.Invoice
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.invoice.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val repository: InvoiceRepository,
) : ViewModel() {


    private val _invoiceById = MutableStateFlow<NetworkResult<List<Invoice>>>(NetworkResult.Loading())
    val invoiceById: StateFlow<NetworkResult<List<Invoice>>> = _invoiceById

    private val _invoiceByUserId = MutableStateFlow<NetworkResult<List<Invoice>>>(NetworkResult.Loading())
    val invoiceByUserId: StateFlow<NetworkResult<List<Invoice>>> = _invoiceByUserId

    fun getInvoiceById(id: Long, token: String) {
        viewModelScope.launch {
            _invoiceById.value = repository.getInvoiceById(id, token)
        }
    }

    fun getInvoiceByUserId(pageIndex: Int, pageSize: Int, userId: Long, token: String) {
        viewModelScope.launch {
            _invoiceByUserId.value = repository.getInvoiceByUserId(pageIndex, pageSize, userId, token)
        }
    }

}