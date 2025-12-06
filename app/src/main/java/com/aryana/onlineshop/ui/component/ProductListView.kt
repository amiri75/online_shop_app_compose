package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aryana.onlineshop.vm.HomeViewModel

@Composable
fun ProductListView(
    homeViewModel: HomeViewModel,
) {

    val products by homeViewModel.products.collectAsState()

    DataUiStateHandler(
        networkResult = products,
        modifierLoading = Modifier.modifierLoading()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            for ((index, item) in (products.data)?.withIndex() ?: emptyList()) {
                AnimatedSlideIn(index * 100) {
                    AppCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        image = item.image,
                        title = item.title,
                    )
                }

            }
        }
    }
}