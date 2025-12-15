package com.aryana.onlineshop.ui.navcontoroller

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aryana.onlineshop.ui.screen.BasketScreen
import com.aryana.onlineshop.ui.screen.HomeScreen
import com.aryana.onlineshop.ui.screen.ProductsScreen
import com.aryana.onlineshop.ui.screen.SingleProductScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
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
            route = Screens.Products.route + "/{catId}/{title}",
            arguments = listOf(
                navArgument("catId") {
                    type = NavType.LongType
                },
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) {
            ProductsScreen(navController)
        }

        composable(
            route = Screens.ShowProduct.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) {
            SingleProductScreen(navController,innerPadding)
        }

        composable(
            route = Screens.Basket.route,
            arguments = listOf()
        ) {
            BasketScreen(navController)
        }
    }
}
