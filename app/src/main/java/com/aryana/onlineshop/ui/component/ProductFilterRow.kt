package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aryana.onlineshop.ui.theme.textColorApp
import com.aryana.onlineshop.vm.HomeViewModel

@Composable
fun ProductFilterRow(
    homeViewModel: HomeViewModel,
) {
    val filter = listOf("All", "New", "Popular")
    var selectedFilter by remember {
        mutableIntStateOf(0)
    }
    LazyRow(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(filter) { index, item ->
            AnimatedSlideIn(index * 100) {
                TextButton(
                    onClick = {
                        selectedFilter = index
                        homeViewModel.loadFilterProduct(selectedFilter)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (selectedFilter == index) Color.LightGray else Color.Transparent,
                        contentColor = textColorApp
                    ),
                    modifier = Modifier.width(90.dp)
                ) {
                    Text(text = item, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                }
            }
        }
    }
}


