package com.aryana.onlineshop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryana.onlineshop.ui.component.AnimatedSlideIn
import com.aryana.onlineshop.ui.component.AppCard
import com.aryana.onlineshop.ui.navcontoroller.Screens
import com.aryana.onlineshop.vm.ProductsViewModel

@Composable
fun ProductsScreen(
    navHostController: NavHostController,
    productsViewModel: ProductsViewModel = hiltViewModel(),
) {

    val products by productsViewModel.products.collectAsState()
    val isLoading by productsViewModel.isLoading.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        AnimatedSlideIn() {
            Text(text = productsViewModel.title, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            itemsIndexed(products) { index, item ->
                AnimatedSlideIn(index * 100) {
                    AppCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        image = item.image,
                        title = item.title,
                    ){
                        navHostController.navigate(Screens.ShowProduct.route+"/${item.id}")
                    }
                }
                if (index >= products.size - 1 && !isLoading) {
                    LaunchedEffect(Unit) {
                        productsViewModel.loadNextPage()
                    }
                }
            }
        }
    }
}