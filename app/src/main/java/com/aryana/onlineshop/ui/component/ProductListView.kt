package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aryana.onlineshop.ui.navcontoroller.Screens
import com.aryana.onlineshop.vm.HomeViewModel

@Composable
fun ProductListView(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
) {

    val products by homeViewModel.products.collectAsState()

    DataUiStateHandler(
        networkResult = products,
        modifier = Modifier.modifierLoading()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            products.data?.forEachIndexed { index, product ->
                key(product.id) {
                    AnimatedSlideIn(index * 100) {
                        AppCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            image = product.image,
                            title = product.title,
                        ){
                            navHostController.navigate(Screens.ShowProduct.route+"/${product.id}")
                        }
                    }
                }
            }
        }
    }
}