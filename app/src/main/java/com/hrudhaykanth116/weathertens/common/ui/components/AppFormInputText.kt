package com.hrudhaykanth116.weathertens.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.hrudhaykanth116.weathertens.common.ui.models.InputType
import com.hrudhaykanth116.weathertens.common.ui.models.TextFieldData

@Preview
@Composable
fun AppFormInputTextPreview() {
    AppFormInputText(
        textFieldData = TextFieldData(
            inputValue = TextFieldValue(),
            inputType = InputType.RegularInputType,
            label = "User name",
            hint = "Enter user name",
            error = "Field cannot be empty."
        )
    )
}

@Composable
fun AppFormInputText(
    textFieldData: TextFieldData,
    onInputChange: (TextFieldValue) -> Unit = {}
) {

    Column() {
        // if (!label.isNullOrBlank()) {
        //     Text(
        //         text = label,
        //         // modifier = Modifier.padding(8.dp),
        //         style = MaterialTheme.typography.h5
        //     )
        // }

        when (textFieldData.inputType) {
            is InputType.PwdInputType -> {
                AppPwdTextField(textFieldData, onInputChange)
            }
            is InputType.RegularInputType -> {
                AppTextField(
                    textFieldData, onInputChange = onInputChange
                )
            }
            InputType.EmailInputType -> {
                AppTextField(
                    textFieldData, onInputChange = onInputChange,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                    )
                )
            }
        }
        if (!textFieldData.error.isNullOrBlank()) {
            Text(
                text = textFieldData.error,
                color = Color.Red
            )
        }

    }

}

@Composable
private fun AppPwdTextField(
    textFieldData: TextFieldData,
    onInputChange: (TextFieldValue) -> Unit,
) {

    // State hoisting is not done for now. See if needed.
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    AppTextField(
        textFieldData, onInputChange = onInputChange,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

@Composable
fun AppTextField(
    textFieldData: TextFieldData,
    onInputChange: (TextFieldValue) -> Unit = {},
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        value = textFieldData.inputValue,
        isError = textFieldData.error?.isNotBlank() == true,
        onValueChange = onInputChange,
        // visualTransformation = PasswordVisualTransformation(),
        label = {
            textFieldData.label?.let { Text(text = it) }
        },
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions
    )
}