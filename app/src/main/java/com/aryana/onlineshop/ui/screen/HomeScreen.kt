package com.aryana.onlineshop.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryana.onlineshop.ui.component.ProductCategoriesRow
import com.aryana.onlineshop.ui.component.ProductsView
import com.aryana.onlineshop.ui.component.SlidersRow
import com.aryana.onlineshop.vm.HomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SlidersRow(homeViewModel)
        Spacer(Modifier.height(8.dp))
        ProductCategoriesRow(navHostController,homeViewModel)
        Spacer(Modifier.height(8.dp))
        ProductsView(navHostController,homeViewModel)
    }
}


