package com.aryana.onlineshop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aryana.onlineshop.db.entity.BasketEntity
import com.aryana.onlineshop.model.customer.UserDto
import com.aryana.onlineshop.model.invoice.InvoiceItem
import com.aryana.onlineshop.model.invoice.PaymentTransaction
import com.aryana.onlineshop.model.invoice.mapper.toInvoiceItem
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.repository.invoice.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
) : ViewModel() {


    fun gotoPayment(
        userDto: UserDto,
        basket: List<BasketEntity>,
        loginViewModel: LoginViewModel,
        onLoading: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,

        ) {
        val items = mutableListOf<InvoiceItem>()
        basket.forEach {
            items.add(it.toInvoiceItem())
        }
        val trx = PaymentTransaction(items = items, user = userDto)
        viewModelScope.launch {
            when (val uriTrx = transactionRepository.gotoPayment(trx)) {
                is NetworkResult.Success -> {
                    if (uriTrx.data != null) {
                        loginViewModel.login(userDto.username ?: "", userDto.password ?: "")
                        onSuccess(uriTrx.data[0])
                    }
                }

                is NetworkResult.Error -> {
                    if (uriTrx.message != null) {
                        onError(uriTrx.message)
                    }
                }

                is NetworkResult.Loading -> {
                    onLoading()
                }
            }

        }
    }

}