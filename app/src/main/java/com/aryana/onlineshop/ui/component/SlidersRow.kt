package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aryana.onlineshop.model.site.Slider
import com.aryana.onlineshop.vm.HomeViewModel

@Composable
fun SlidersRow(homeViewModel: HomeViewModel) {

    val slidersResult by homeViewModel.slides.collectAsState()

    DataUiStateHandler(
        networkResult = slidersResult,
        modifier = Modifier.modifierLoading()
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(slidersResult.data as List<Slider>) { index, item ->
                AnimatedSlideIn(index * 100) {
                    AppCard(
                        modifier = Modifier.size(300.dp,200.dp),
                        image = item.image,
                        title = item.title,
                        subTitle = item.subTitle
                    )
                }
            }
        }
    }
}









