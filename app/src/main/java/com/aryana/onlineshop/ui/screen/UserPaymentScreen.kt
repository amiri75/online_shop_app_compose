package com.aryana.onlineshop.ui.screen

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.aryana.onlineshop.R
import com.aryana.onlineshop.model.customer.UserDto
import com.aryana.onlineshop.ui.component.AppTextFiled
import com.aryana.onlineshop.ui.navcontoroller.Screens
import com.aryana.onlineshop.vm.BasketViewModel
import com.aryana.onlineshop.vm.LoginViewModel
import com.aryana.onlineshop.vm.PaymentViewModel
import com.aryana.onlineshop.vm.UserViewModel

@Composable
fun UserPaymentScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    paymentViewModel: PaymentViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val basket by basketViewModel.getAllBasket.collectAsState()


    val user by userViewModel.currentUser.collectAsState()

    var loading by remember {
        mutableStateOf(false)
    }

    var textFieldFirstName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textFieldLastName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textFieldPhone by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textFieldUsername by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textFieldPassword by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textFieldPostalCode by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var textFieldAddress by remember {
        mutableStateOf(TextFieldValue(""))
    }
    LaunchedEffect(user) {
        user?.let {
            textFieldFirstName = TextFieldValue(it.firstName ?: "")
            textFieldLastName = TextFieldValue(it.lastName ?: "")
            textFieldPhone = TextFieldValue(it.phone ?: "")
            textFieldUsername = TextFieldValue(it.username ?: "")
            textFieldPostalCode = TextFieldValue(it.postalCode ?: "")
            textFieldAddress = TextFieldValue(it.address ?: "")
        }
    }

    var firstNameError by remember {
        mutableStateOf(false)
    }
    var lastNameError by remember {
        mutableStateOf(false)
    }
    var phoneError by remember {
        mutableStateOf(false)
    }
    var usernameError by remember {
        mutableStateOf(false)
    }
    var passwordError by remember {
        mutableStateOf(false)
    }
    var postalCodeError by remember {
        mutableStateOf(false)
    }
    var addressError by remember {
        mutableStateOf(false)
    }
    var passwordVisible by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(painter = painterResource(R.drawable.arrow_back), contentDescription = null)
            }
            Spacer(Modifier.width(4.dp))
            Text("Complete Your Information", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                AppTextFiled(
                    textField = textFieldFirstName,
                    label = "First Name",
                    isError = firstNameError,
                    onValueChange = {
                        textFieldFirstName = it
                        firstNameError = false
                    },
                )
            }
            item {
                AppTextFiled(
                    textField = textFieldLastName,
                    label = "Last Name",
                    isError = lastNameError,
                    onValueChange = {
                        textFieldLastName = it
                        lastNameError = false
                    },

                    )
            }
            item {
                AppTextFiled(
                    textField = textFieldPhone,
                    label = "Phone",
                    isError = phoneError,
                    keyboardType = KeyboardType.Phone,
                    onValueChange = {
                        textFieldPhone = it
                        phoneError = false
                    },

                    )
            }
            item {
                AppTextFiled(
                    textField = textFieldUsername,
                    label = "Username",
                    isError = usernameError,
                    onValueChange = {
                        textFieldUsername = it
                        usernameError = false
                    },
                    enabled = user == null
                )

            }
            item {
                if (user == null) {
                    AppTextFiled(
                        textField = textFieldPassword,
                        label = "Password",
                        isError = passwordError,
                        keyboardType = KeyboardType.Password,
                        onValueChange = {
                            textFieldPassword = it
                            passwordError = false
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        passwordVisible = {
                            IconButton(
                                onClick = { passwordVisible = !passwordVisible }
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (passwordVisible) R.drawable.visibility else R.drawable.visibility_off
                                    ),
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }
            item {
                AppTextFiled(
                    textField = textFieldPostalCode,
                    label = "PostalCode",
                    isError = postalCodeError,
                    keyboardType = KeyboardType.Phone,
                    onValueChange = {
                        textFieldPostalCode = it
                        postalCodeError = false
                    },

                    )
            }
            item {
                AppTextFiled(
                    textField = textFieldAddress,
                    label = "Address",
                    isError = addressError,
                    imeAction = ImeAction.Done,
                    singleLine = false,
                    onValueChange = {
                        textFieldAddress = it
                        addressError = false
                    },

                    )
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = {
                    firstNameError = textFieldFirstName.text.isEmpty()
                    lastNameError = textFieldLastName.text.isEmpty()
                    phoneError = textFieldPhone.text.isEmpty()
                    usernameError = textFieldUsername.text.isEmpty()
                    passwordError = user == null && textFieldPassword.text.isEmpty()
                    postalCodeError = textFieldPostalCode.text.isEmpty()
                    addressError = textFieldAddress.text.isEmpty()
                    if (firstNameError ||
                        lastNameError ||
                        phoneError ||
                        usernameError ||
                        passwordError ||
                        postalCodeError ||
                        addressError
                    ) {
                        return@Button
                    }
                    val userDto = UserDto(
                        id = if (user == null) null else user?.userId,
                        customerId = if (user == null) null else user?.customerId,
                        address = textFieldAddress.text,
                        firstName = textFieldFirstName.text,
                        lastName = textFieldLastName.text,
                        password = textFieldPassword.text,
                        phone = textFieldPhone.text,
                        postalCode = textFieldPostalCode.text,
                        username = textFieldUsername.text
                    )

                    paymentViewModel.gotoPayment(
                        userDto, basket,
                        onError = {
                            loading = false
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        },
                        onLoading = {
                            loading = true
                        },
                        onSuccess = {
                            loading = false
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = it.toUri()
                            context.startActivity(intent)
                            navController.navigate(Screens.Home.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = false
                            }
                            basketViewModel.clearBasket()
                        },
                        loginViewModel = loginViewModel
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                if (loading) {
                    CircularProgressIndicator()
                } else {
                    Text($$"$Pay")
                }

            }

        }
        Spacer(Modifier.height(8.dp))
    }
}
