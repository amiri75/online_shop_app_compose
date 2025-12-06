package com.aryana.onlineshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aryana.onlineshop.ui.screen.OnlineShopApp
import com.aryana.onlineshop.ui.theme.OnlineshopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineshopTheme {
                OnlineShopApp()
            }
        }
    }
}
