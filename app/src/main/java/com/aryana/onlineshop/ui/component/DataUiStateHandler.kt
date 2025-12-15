package com.aryana.onlineshop.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.aryana.onlineshop.network.NetworkResult


@Composable
fun <T> DataUiStateHandler(
    networkResult: NetworkResult<T>,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = {
        Loading(modifier = modifier)
    },
    errorContent: @Composable () -> Unit = {
        ToastError(context = LocalContext.current, errorMessage = networkResult.message)
    },

    successContent: @Composable () -> Unit,
) {
    when (networkResult) {
        is NetworkResult.Loading -> loadingContent()
        is NetworkResult.Error -> errorContent()
        is NetworkResult.Success -> successContent()
    }
}