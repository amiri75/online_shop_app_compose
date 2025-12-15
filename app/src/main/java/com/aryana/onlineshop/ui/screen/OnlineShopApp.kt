package com.aryana.onlineshop.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.aryana.onlineshop.ui.component.TopNavBar
import com.aryana.onlineshop.ui.component.CheckForFullScreen
import com.aryana.onlineshop.ui.navcontoroller.SetupNavGraph

@Composable
fun OnlineShopApp() {

    val navHostController = rememberNavController()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (!CheckForFullScreen(navHostController))
                    TopNavBar(navHostController)
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        if (!CheckForFullScreen(navHostController))
                            innerPadding
                        else
                            PaddingValues(0.dp)
                    )
            ) {
                SetupNavGraph(navHostController,innerPadding)
            }
        }
    }
}

