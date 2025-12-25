package com.aryana.onlineshop.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryana.onlineshop.R
import com.aryana.onlineshop.ui.navcontoroller.Screens
import com.aryana.onlineshop.vm.UserViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel(),
) {

    val user by userViewModel.currentUser.collectAsState()

    var isShowDialog by remember {
        mutableStateOf(false)
    }

    if (isShowDialog) {
        ShowDialogLogout(
            onConfirm = {
                userViewModel.logout()
                isShowDialog = false
                navController.navigate(Screens.Home.route) {
                    popUpTo(Screens.Home.route) {
                        inclusive = true
                    }
                }
            },
            onDismiss = {
                isShowDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(65.dp)
                    .border(2.dp, Color.LightGray, CircleShape)
            ) {
                Image(painter = painterResource(R.drawable.avatar), contentDescription = null)
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text(text = user?.firstName ?: "", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "@${user?.username}", fontSize = 14.sp, color = Color.Gray)
            }
        }
        Spacer(Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(Modifier.height(16.dp))
        CartItemProfile(
            title = "Invoice",
            icon = R.drawable.invoice,
        ) {
            navController.navigate(Screens.Invoices.route)
        }
        Spacer(Modifier.height(8.dp))
        CartItemProfile(
            title = "Change Password",
            icon = R.drawable.change_password,
        ) {
            navController.navigate(Screens.ChangePassword.route)
        }
        Spacer(Modifier.height(8.dp))
        CartItemProfile(
            title = "Help",
            icon = R.drawable.help,
        ) {}
        Spacer(Modifier.height(8.dp))
        CartItemProfile(
            title = "Logout",
            icon = R.drawable.logout,
            colorIcon = Color.Red,
            colorTitle = Color.Red

        ) {
            isShowDialog = true
        }
    }

}

@Composable
fun ShowDialogLogout(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Yes", color = Color.Red)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("No")
            }
        },
        title = {},
        text = { Text("Are you sure want to logout?", fontWeight = FontWeight.SemiBold, fontSize = 16.sp) }
    )
}

@Composable
fun CartItemProfile(
    title: String,
    icon: Int,
    colorIcon: Color = Color.Black,
    colorTitle: Color = Color.Unspecified,
    onClick: () -> Unit,
) {
    Card(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = colorIcon,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(text = title, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = colorTitle)
        }
    }
}