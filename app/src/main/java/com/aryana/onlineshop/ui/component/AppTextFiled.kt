package com.aryana.onlineshop.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aryana.onlineshop.R

@Composable
fun AppTextFiled(
    textField: TextFieldValue,
    label: String,
    isError: Boolean,
    singleLine: Boolean = true,
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (TextFieldValue) -> Unit,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    shape: RoundedCornerShape = RoundedCornerShape(6.dp),
    enabled: Boolean = true,
    passwordVisible:@Composable () -> Unit = {}
) {

    Column {
        OutlinedTextField(
            onValueChange = {
                onValueChange(it)
            },
            value = textField,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            shape =shape,
            singleLine = singleLine,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            isError = isError,
            trailingIcon = {
                if (isError) {
                    Icon(painter = painterResource(R.drawable.warning), contentDescription = null)
                }else{
                    passwordVisible()
                }

            },
            visualTransformation = visualTransformation,
            colors = colors,
            enabled= enabled,
        )
        if (isError) {
            Text("Please enter your $label", color = Color.Red, fontSize = 12.sp)
        }
    }
}