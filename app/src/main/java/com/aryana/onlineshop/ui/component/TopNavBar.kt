package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.aryana.onlineshop.R
import com.aryana.onlineshop.ui.navcontoroller.Screens
import com.aryana.onlineshop.vm.BasketViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(
    navHostController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
) {


    val countBasket by basketViewModel.getCountBasket.collectAsStateWithLifecycle()

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
                    onClick = {
                        navHostController.navigate(Screens.Basket.route){
                            launchSingleTop = true
                        }
                    }
                ) {
                    BadgedBox(
                        badge = {
                            if (countBasket != 0)
                            Badge {
                                Text("$countBasket")
                            }
                        }
                    ) {
                        Icon(painterResource(R.drawable.shopping_cart), contentDescription = null, modifier = Modifier.size(22.dp))
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


