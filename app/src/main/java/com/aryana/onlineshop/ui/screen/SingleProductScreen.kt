package com.aryana.onlineshop.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryana.onlineshop.R
import com.aryana.onlineshop.model.product.Product
import com.aryana.onlineshop.ui.component.AnimatedSlideIn
import com.aryana.onlineshop.ui.component.AppGradient
import com.aryana.onlineshop.ui.component.AppImage
import com.aryana.onlineshop.ui.component.Loading
import com.aryana.onlineshop.ui.theme.textDetailProduct
import com.aryana.onlineshop.ui.utils.formatPrice
import com.aryana.onlineshop.vm.BasketViewModel
import com.aryana.onlineshop.vm.SingleProductViewModel

@Composable
fun SingleProductScreen(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    singleProductViewModel: SingleProductViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel(),
) {


    val context = LocalContext.current
    val product by singleProductViewModel.product.collectAsState()
    val loading by singleProductViewModel.loading.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (loading) {
            Loading(modifier = Modifier.fillMaxSize())
        } else {

            AppImage(product?.image ?: "")
            AppGradient(modifier = Modifier.height(600.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                IconButton(
                    onClick = {
                        navHostController.popBackStack()
                    }
                ) {
                    Icon(painterResource(R.drawable.arrow_back), contentDescription = null)
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 16.dp),
                ) {
                    AnimatedSlideIn(100) {
                        Text("${product?.title}", color = textDetailProduct, fontWeight = FontWeight.Bold, fontSize = 26.sp)
                    }
                    Spacer(Modifier.height(8.dp))
                    AnimatedSlideIn(200) {
                        Text("${formatPrice(product?.price)} T", color = Color.White, fontSize = 18.sp)
                    }

                    Spacer(Modifier.height(16.dp))
                    AnimatedSlideIn(400) {
                        Text("Size", color = textDetailProduct, fontSize = 18.sp)
                    }
                    Spacer(Modifier.height(4.dp))
                    AnimatedSlideIn(500) {

                        ProductSizeRow(product, singleProductViewModel)
                    }
                    Spacer(Modifier.height(16.dp))
                    AnimatedSlideIn(600) {
                        Text("Colors", color = textDetailProduct, fontSize = 18.sp)
                    }
                    Spacer(Modifier.height(4.dp))
                    AnimatedSlideIn(700) {

                        ProductColorRow(product, singleProductViewModel)
                    }
                    Spacer(Modifier.height(16.dp))

                    AnimatedSlideIn(300) {
                        Text("${product?.description}", color = Color.White.copy(0.7f), fontSize = 12.sp)
                    }
                    Spacer(Modifier.height(16.dp))
                    AnimatedSlideIn(800) {
                        Button(
                            onClick = {
                                basketViewModel.addToBasket(
                                    product = product,
                                    color = singleProductViewModel.selectedColor,
                                    size = singleProductViewModel.selectedSize
                                )
                                Toast.makeText(context, "add to basket", Toast.LENGTH_SHORT).show()
                                navHostController.popBackStack()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Buy Now")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductColorRow(product: Product?, singleProductViewModel: SingleProductViewModel) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        itemsIndexed(product?.colors ?: emptyList()) { index, item ->
            Card(
                modifier = Modifier.size(30.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color("#${item.hexValue}".toColorInt())
                ),
                onClick = { singleProductViewModel.selectedColor = item }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (singleProductViewModel.selectedColor == item) {
                        val checkColorWithe = Color("#${item.hexValue}".toColorInt()) == Color(0xffffffff)
                        Icon(
                            painterResource(R.drawable.check_small), contentDescription = null,
                            tint = if (checkColorWithe) Color.Black else Color.White
                        )
                    }

                }
            }
        }
    }
}

@Composable
private fun ProductSizeRow(product: Product?, singleProductViewModel: SingleProductViewModel) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        itemsIndexed(product?.sizes ?: emptyList()) { index, item ->
            Card(
                modifier = Modifier.size(40.dp),
                colors = CardDefaults.cardColors(
                    contentColor = if (singleProductViewModel.selectedSize == item) Color.Black else Color.White,
                    containerColor = if (singleProductViewModel.selectedSize == item) Color.White else Color.Transparent,
                ),
                onClick = { singleProductViewModel.selectedSize = item }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(item.title?: "", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
