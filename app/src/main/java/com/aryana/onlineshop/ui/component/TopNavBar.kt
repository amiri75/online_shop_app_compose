package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aryana.onlineshop.R
import com.aryana.onlineshop.ui.navcontoroller.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navHostController: NavHostController) {
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    val isShow = currentRoute?.startsWith(Screens.Home.route) ?: false
    if (isShow) {
        TopAppBar(
            title = {
                AnimatedSlideIn {
                    Image(
                        painterResource(R.drawable.logo), contentDescription = null,
                        modifier = Modifier.width(160.dp)
                    )
                }

            },
            actions = {
                AnimatedSlideIn {
                    IconButton(
                        onClick = {}
                    ) {
                        BadgedBox(
                            badge = {
                                Badge { Text("2") }
                            }
                        ) {
                            Icon(painterResource(R.drawable.shopping_cart), contentDescription = null)
                        }

                    }
                }
                AnimatedSlideIn {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(painterResource(R.drawable.person), contentDescription = null)
                    }
                }

            }
        )
    }
}