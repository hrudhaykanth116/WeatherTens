package com.hrudhaykanth116.weathertens.common.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hrudhaykanth116.weathertens.common.utils.compose.MyPreview

@MyPreview
@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    text: String = "Test toolbar",
) {

    TopAppBar(
        title = { Text(text = "Register", color = Color.White) },
        backgroundColor = Color.Gray
    )
/*
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(50.dp)
            .background(
                color = Color.Gray
            ),
        fontSize = 20.sp,
        color = Color.White,
    )*/

}