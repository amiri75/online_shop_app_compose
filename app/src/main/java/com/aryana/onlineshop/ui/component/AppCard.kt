package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    image: String? = null,
    title: String? = null,
    subTitle: String? = null,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), clip = true),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AppImage(image ?: "")
            AppGradient(Modifier.height(50.dp))
            Column(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(title ?: "", color = Color.White, fontSize = 14.sp, fontWeight = SemiBold)
                if (subTitle != null) {
                    Text(subTitle, color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}