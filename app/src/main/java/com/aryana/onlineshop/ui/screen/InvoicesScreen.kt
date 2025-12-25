package com.aryana.onlineshop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryana.onlineshop.R
import com.aryana.onlineshop.ui.component.AnimatedSlideIn
import com.aryana.onlineshop.ui.navcontoroller.Screens
import com.aryana.onlineshop.vm.InvoiceViewModel
import com.aryana.onlineshop.vm.UserViewModel


@Composable
fun InvoicesScreen(
    navHostController: NavHostController,
    invoiceViewModel: InvoiceViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
) {

    val currentUser by userViewModel.currentUser.collectAsState()
    val invoices by invoiceViewModel.invoices.collectAsState()
    val isLoading by invoiceViewModel.isLoading.collectAsState()

    val listState = rememberLazyListState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            lastVisibleIndex >= totalItemsCount - 2
        }
    }

    if (currentUser != null) {
        LaunchedEffect(shouldLoadMore) {
            if (shouldLoadMore && !isLoading) {
                invoiceViewModel.loadInvoices(userId = currentUser?.userId ?: 0, token = currentUser?.token ?: "")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Invoices", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(invoices.size) { index ->
                AnimatedSlideIn(index * 100) {
                    CartItemProfile(
                        title = "${invoices[index].addDate} (${invoices[index].status})",
                        icon = if (invoices[index].status == "NotPayed") R.drawable.delete else R.drawable.check_small,
                        colorTitle = if (invoices[index].status == "NotPayed") Color.Red else Color.Green,
                        colorIcon = if (invoices[index].status == "NotPayed") Color.Red else Color.Green
                    ) {
                        navHostController.navigate(Screens.SingleInvoice.route + "/${invoices[index].id}")
                    }
                }
            }
        }
    }
}
