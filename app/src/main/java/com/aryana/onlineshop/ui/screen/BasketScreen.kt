package com.aryana.onlineshop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.aryana.onlineshop.R
import com.aryana.onlineshop.db.entity.BasketEntity
import com.aryana.onlineshop.ui.component.AnimatedSlideIn
import com.aryana.onlineshop.ui.component.AppImage
import com.aryana.onlineshop.ui.navcontoroller.Screens
import com.aryana.onlineshop.ui.theme.AppGreen
import com.aryana.onlineshop.ui.utils.formatPrice
import com.aryana.onlineshop.vm.BasketViewModel

@Composable
fun BasketScreen(
    navHostController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
) {

    val allBasket by basketViewModel.getAllBasket.collectAsStateWithLifecycle()

    val totalPrice  =formatPrice(allBasket.sumOf { it.price * it.quantity })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text("Cart", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        if (allBasket.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Basket is empty", fontWeight = Bold, fontSize = 18.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                itemsIndexed(allBasket) { index, item ->
                    AnimatedSlideIn(
                        index * 100
                    ) {
                        Column {
                            CartItem(
                                item = item,
                                onIncrease = {
                                    basketViewModel.increaseQuantity(it.productId, it.colorId, it.sizeId)
                                },
                                onDecrease = {
                                    basketViewModel.decreaseQuantity(it.productId, it.colorId, it.sizeId)
                                },
                                onRemove = {
                                    basketViewModel.deleteItem(it)
                                }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(10.dp))
        AnimatedSlideIn(400) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("$totalPrice T", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                }
                Spacer(Modifier.height(18.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            navHostController.navigate(Screens.Home.route) {
                                popUpTo(Screens.Home.route) {
                                    inclusive = true
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
                    ) {
                        Text("Continue Shopping")
                    }
                    Button(
                        onClick = {
                            navHostController.navigate(Screens.UserPayment.route)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = AppGreen)
                    ) {
                        Text("Proceed to Payment")
                    }
                }
            }
        }
    }
}

@Composable
fun CartItem(
    item: BasketEntity,
    onIncrease: (BasketEntity) -> Unit,
    onDecrease: (BasketEntity) -> Unit,
    onRemove: (BasketEntity) -> Unit,
) {
    var isShowDialog by remember {
        mutableStateOf(false)
    }
    if (isShowDialog) {
        DialogDeleteItem(
            onDelete = {
                onRemove(item)
                isShowDialog = false
            },
            onCancel = {
                isShowDialog = false
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Card(
                modifier = Modifier.size(60.dp)
            ) {
                AppImage(item.image.toString())
            }
            Column {
                Text(text = item.title.toString(), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "${formatPrice((item.price) * (item.quantity))} T", fontSize = 13.sp)
            }
            Spacer(Modifier.weight(1f))

            Column(
            ) {
                if (item.size != null) {
                    Text("size ${item.size}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(6.dp))
                if (item.colorHex != null) {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(20.dp)
                            .shadow(6.dp, shape = RoundedCornerShape(16.dp))
                            .background(Color("#${item.colorHex}".toColorInt()), shape = RoundedCornerShape(16.dp))

                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        onDecrease(item)
                    }
                ) { Icon(painter = painterResource(R.drawable.decrease), contentDescription = null) }

                Text(item.quantity.toString(), Modifier.padding(horizontal = 8.dp))
                IconButton(
                    onClick = {
                        onIncrease(item)
                    }
                ) { Icon(painter = painterResource(R.drawable.increase), contentDescription = null) }

            }
            IconButton(
                onClick = {
                    isShowDialog = true
                }
            ) { Icon(painter = painterResource(R.drawable.delete), contentDescription = null, tint = Color.Red) }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogDeleteItem(
    onDelete: () -> Unit,
    onCancel: () -> Unit,
) {

    AlertDialog(
        onDismissRequest = {
            onCancel()
        },
        title = { Text("Delete Item") },
        text = { Text("Do you want to delete item?") },
        confirmButton = {
            TextButton(
                onClick = { onDelete() }
            ) {
                Text("Delete", color = Color.Red)
            }
        },
        dismissButton = {

            TextButton(
                onClick = { onCancel() }
            ) {
                Text("Cancel")
            }

        }
    )
}