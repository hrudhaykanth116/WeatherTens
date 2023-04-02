package com.hrudhaykanth116.weathertens.common.ui.models

import androidx.compose.ui.text.input.TextFieldValue

data class TextFieldData(
    val inputValue: TextFieldValue,
    val inputType: InputType,
    val label: String? = null,
    val hint: String? = null,
    val error: String? = null,
)

sealed class InputType{
    object RegularInputType: InputType()
    object EmailInputType: InputType()
    object PwdInputType: InputType()
}

