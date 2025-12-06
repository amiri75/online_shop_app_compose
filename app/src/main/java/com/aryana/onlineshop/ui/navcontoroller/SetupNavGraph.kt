package com.aryana.onlineshop.ui.navcontoroller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aryana.onlineshop.ui.screen.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
    ) {
        composable(
            route = Screens.Home.route,
            arguments = listOf()
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screens.Products.route + "?{catId}",
            arguments = listOf()
        ) {
            HomeScreen(navController)
        }
    }
}
