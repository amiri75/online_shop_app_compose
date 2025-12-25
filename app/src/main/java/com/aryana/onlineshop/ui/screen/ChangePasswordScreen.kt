package com.aryana.onlineshop.ui.screen

import android.R.attr.data
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aryana.onlineshop.R
import com.aryana.onlineshop.ui.component.AppTextFiled
import com.aryana.onlineshop.vm.LoginViewModel

@Composable
fun ChangePasswordScreen(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    var oldPassword by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var newPassword by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var repeatPassword by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var oldPasswordError by remember {
        mutableStateOf(false)
    }
    var newPasswordError by remember {
        mutableStateOf(false)
    }
    var repeatPasswordError by remember {
        mutableStateOf(false)
    }

    var oldPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var repeatPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Change Password", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(16.dp))
        AppTextFiled(
            textField = oldPassword,
            label = "Old Password",
            isError = oldPasswordError,
            onValueChange = {
                oldPassword = it
                oldPasswordError = false
            },
            keyboardType = KeyboardType.Password,
            visualTransformation = if (oldPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            passwordVisible = {
                IconButton(
                    onClick = {oldPasswordVisible = !oldPasswordVisible}
                ) {
                    Icon(
                        painter = painterResource(
                            if (oldPasswordVisible) R.drawable.visibility else R.drawable.visibility_off),
                        contentDescription = null)
                }
            }
        )
        AppTextFiled(
            textField = newPassword,
            label = "New Password",
            isError = newPasswordError,
            onValueChange = {
                newPassword = it
                newPasswordError = false
            },
            keyboardType = KeyboardType.Password,
            visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            passwordVisible = {
                IconButton(
                    onClick = {newPasswordVisible = !newPasswordVisible}
                ) {
                    Icon(
                        painter = painterResource(
                            if (newPasswordVisible) R.drawable.visibility else R.drawable.visibility_off),
                        contentDescription = null)
                }
            }
        )
        AppTextFiled(
            textField = repeatPassword,
            label = "Repeat Password",
            isError = repeatPasswordError,
            onValueChange = {
                repeatPassword = it
                repeatPasswordError = false
            },
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            visualTransformation =if (repeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            passwordVisible = {
                IconButton(
                    onClick = {repeatPasswordVisible = !repeatPasswordVisible}
                ) {
                    Icon(
                        painter = painterResource(
                            if (repeatPasswordVisible) R.drawable.visibility else R.drawable.visibility_off),
                        contentDescription = null)
                }
            }
        )

        Button(
            onClick = {
                oldPasswordError = oldPassword.text.isEmpty()
                newPasswordError = newPassword.text.isEmpty()
                repeatPasswordError = repeatPassword.text.isEmpty()

                if (oldPasswordError || newPasswordError || repeatPasswordError) {
                    return@Button
                }
               loginViewModel.changePassword(
                   oldPassword = oldPassword.text,
                   newPassword = newPassword.text,
                   repeatPassword = repeatPassword.text,
                   onSuccess = {message,data ->
                       if (data == null) {
                           Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                       }
                       if (message == "") {
                           Toast.makeText(context, "Change Password successfully ", Toast.LENGTH_SHORT).show()
                           navHostController.popBackStack()
                       }
                   },
                   onLoading = {

                   },
                   onError = {
                       Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                   }
               )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ){
            Text("Change Password")
        }

    }


}