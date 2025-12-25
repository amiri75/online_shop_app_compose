package com.aryana.onlineshop.ui.navcontoroller

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aryana.onlineshop.ui.screen.BasketScreen
import com.aryana.onlineshop.ui.screen.ChangePasswordScreen
import com.aryana.onlineshop.ui.screen.HomeScreen
import com.aryana.onlineshop.ui.screen.InvoicesScreen
import com.aryana.onlineshop.ui.screen.LoginScreen
import com.aryana.onlineshop.ui.screen.ProductsScreen
import com.aryana.onlineshop.ui.screen.ProfileScreen
import com.aryana.onlineshop.ui.screen.SingleInvoiceScreen
import com.aryana.onlineshop.ui.screen.SingleProductScreen
import com.aryana.onlineshop.ui.screen.UserPaymentScreen

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

        composable(
            route = Screens.Login.route,
            arguments = listOf()
        ) {
            LoginScreen(navController)
        }
        composable(
            route = Screens.UserPayment.route,
            arguments = listOf()
        ) {
            UserPaymentScreen(navController)
        }
        composable(
            route = Screens.Profile.route,
            arguments = listOf()
        ) {
            ProfileScreen(navController)
        }
        composable(
            route = Screens.ChangePassword.route,
            arguments = listOf()
        ) {
            ChangePasswordScreen(navController)
        }
        composable(
            route = Screens.Invoices.route,
            arguments = listOf()
        ) {
            InvoicesScreen(navController)
        }
        composable(
            route = Screens.SingleInvoice.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) {
            SingleInvoiceScreen(navController)
        }
    }
}
