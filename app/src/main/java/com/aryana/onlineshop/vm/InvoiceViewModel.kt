package com.aryana.onlineshop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.model.invoice.Invoice
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.invoice.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val invoiceRepository: InvoiceRepository,
) : ViewModel() {

    var invoices = MutableStateFlow<List<Invoice>>(emptyList())
        private set

    var isLoading = MutableStateFlow(false)
        private set

    private var pageIndex = 0

    private val pageSize = 15

    private var endReached = false

    fun loadInvoices(userId: Long, token: String) {
        if (isLoading.value || endReached) return

        viewModelScope.launch {
            isLoading.value = true
            when (val result = invoiceRepository.getInvoiceByUserId(userId,pageIndex, pageSize,  token)) {
                is NetworkResult.Success -> {
                    val newData = result.data ?: emptyList()
                    // اضافه کردن داده‌های جدید به لیست قبلی
                    val current = invoices.value.toMutableList()
                    current.addAll(newData)
                    invoices.value = current
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