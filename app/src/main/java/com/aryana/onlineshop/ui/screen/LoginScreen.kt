package com.aryana.onlineshop.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.aryana.onlineshop.R
import com.aryana.onlineshop.ui.component.AppTextFiled
import com.aryana.onlineshop.ui.navcontoroller.Screens
import com.aryana.onlineshop.ui.theme.AppDarkGray
import com.aryana.onlineshop.vm.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    var textFieldUsername by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textFieldPassword by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var usernameError by remember {
        mutableStateOf(false)
    }
    var passwordError by remember {
        mutableStateOf(false)
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var passwordVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        AppDarkGray,
                        Color.Black
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome Back 👋", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
            Spacer(Modifier.height(16.dp))
            AppTextFiled(
                textField = textFieldUsername,
                label = "Username",
                isError = usernameError,
                onValueChange = {
                    textFieldUsername = it
                    usernameError = false
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                ),
            )

            Spacer(Modifier.height(8.dp))
            AppTextFiled(
                textField = textFieldPassword,
                label = "Password",
                isError = passwordError,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                shape = RoundedCornerShape(12.dp),
                onValueChange = {
                    textFieldPassword = it
                    passwordError = false
                },
                visualTransformation =if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                passwordVisible = {
                    IconButton(
                        onClick = {passwordVisible = !passwordVisible}
                    ) {
                        Icon(
                            painter = painterResource(
                                if (passwordVisible) R.drawable.visibility else R.drawable.visibility_off),
                            contentDescription = null)
                    }
                }
                ,
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                )
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    usernameError = textFieldUsername.text.isEmpty()
                    passwordError = textFieldPassword.text.isEmpty()
                    if (usernameError || passwordError) {
                        return@Button
                    }
                    loginViewModel.login(
                        textFieldUsername.text, textFieldPassword.text,
                        onError = {
                            loading = false
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        },
                        onLoading = {
                            loading = true
                        },
                        onSuccess = {
                            loading = false
                            if (it?.isNotEmpty() == true){
                                Toast.makeText(context, "Welcome Back dear $it", Toast.LENGTH_LONG).show()

                                navController.navigate(Screens.Profile.route){
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = false
                                } 
                            }else{
                                Toast.makeText(context, "username or password is not valid", Toast.LENGTH_SHORT).show()
                            }
                           
                        })
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = !loading
            ) {
                if (loading) {
                    CircularProgressIndicator()
                } else {
                    Text("Login")
                }
            }
        }
    }
}