package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aryana.onlineshop.vm.HomeViewModel

@Composable
fun ProductsView(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel) {

    Column(
        modifier = Modifier
    ) {
        ProductFilterRow(homeViewModel)
        Spacer(Modifier.height(10.dp))
        ProductListView(navHostController,homeViewModel)
    }

}