package com.aryana.onlineshop

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aryana.onlineshop.model.products.Product
import com.aryana.onlineshop.network.NetworkResult
import com.aryana.onlineshop.ui.theme.OnlineshopTheme
import com.aryana.onlineshop.vm.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineshopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GESlider()
                }
            }
        }
    }


}

@Composable
fun GESlider(
    viewModel: ProductViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        // viewModel.getSliders()
        viewModel.getProduct("fa", 1, 5)
    }

    var loading by remember {
        mutableStateOf(false)
    }
//    var sliderById by remember {
//        mutableStateOf(emptyList<Slider>())
//    }

    var productList by remember {
        mutableStateOf(emptyList<Product>())
    }

    val productResult by viewModel.product.collectAsState()


    when (productResult) {
        is NetworkResult.Success -> {
            loading = false
            productList = productResult.data ?: emptyList()
        }

        is NetworkResult.Loading -> {
            loading = true

        }

        is NetworkResult.Error -> {
            loading = false
            Toast.makeText(context, productResult.message, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (loading) {
            CircularProgressIndicator()
        } else {

            LazyColumn() {
                items(productList) {
                    Text(text = it.title)
                }
            }

        }
    }

}