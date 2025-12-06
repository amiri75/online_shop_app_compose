package com.aryana.onlineshop.ui.component

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable

@Composable
fun ToastError(
    context: Context,
    errorMessage: String?,
) {
    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
}