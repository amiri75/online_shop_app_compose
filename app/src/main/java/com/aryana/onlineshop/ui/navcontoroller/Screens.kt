package com.aryana.onlineshop.ui.navcontoroller

sealed class Screens(val route : String) {

    data object Home : Screens("home_screen")
    data object Login : Screens("login_screen")
    data object Basket : Screens("basket_screen")
    data object Products : Screens("products_screen")
    data object ShowProduct : Screens("show_product_screen")
    data object UserPayment : Screens("user_payment_screen")
    data object Profile : Screens("profile_screen")
    data object ChangePassword : Screens("change_password_screen")
    data object Invoices : Screens("invoices_screen")
    data object SingleInvoice : Screens("single_invoice_screen")
}