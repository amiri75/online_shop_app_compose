package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.modifierLoading(): Modifier {
   return Modifier.fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color.Gray.copy(0.5f))
}