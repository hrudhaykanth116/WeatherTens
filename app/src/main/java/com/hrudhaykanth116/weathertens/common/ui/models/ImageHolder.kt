package com.hrudhaykanth116.weathertens.common.ui.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.ImageBitmap

sealed interface ImageHolder{
    data class Bitmap(val imageBitmap: ImageBitmap): ImageHolder
    data class ImageVector(@DrawableRes val resId: Int): ImageHolder
}