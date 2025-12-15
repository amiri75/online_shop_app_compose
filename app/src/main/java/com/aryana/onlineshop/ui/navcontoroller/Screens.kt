package com.aryana.onlineshop.ui.navcontoroller

sealed class Screens(val route : String) {

    data object Home : Screens("home_screen")
    data object Login : Screens("login_screen")
    data object Basket : Screens("basket_screen")
    data object Products : Screens("products_screen")
    data object ShowProduct : Screens("show_product_screen")
}