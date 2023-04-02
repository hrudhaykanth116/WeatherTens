package com.hrudhaykanth116.weathertens.common.ui.components

import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppFormButton(
    btnText: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {

    OutlinedButton(onClick = { onClick?.invoke() }, modifier = modifier) {
        Text(text = btnText)
    }

}

@Composable
@Preview
fun AppButtonPreview(){
    AppFormButton(btnText = "Click me") {
        
    }
}