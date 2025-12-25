package com.aryana.onlineshop.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aryana.onlineshop.ui.navcontoroller.Screens

@Composable
fun checkForFullScreen(navHostController: NavHostController): Boolean {
    val fullScreenRoutes = listOf(Screens.ShowProduct.route, Screens.Login.route)
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route ?: ""

    return fullScreenRoutes.any {
        currentRoute.startsWith(it)
    }

}

