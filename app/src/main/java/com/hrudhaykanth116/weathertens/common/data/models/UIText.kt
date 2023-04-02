package com.hrudhaykanth116.weathertens.common.data.models

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UIText {

    class StringRes(@androidx.annotation.StringRes val stringRes: Int, vararg val formatArgs: Any) : UIText()
    data class Text(val rawString: String): UIText()

    @Composable
    fun getText(): String{
        return when(this){
            is StringRes -> {
                stringResource(id = stringRes, formatArgs = formatArgs)
            }
            is Text -> {
                rawString
            }
        }
    }

    fun getText(context: Context): String{
        return when(this){
            is StringRes -> {
                context.getString(stringRes, formatArgs)
            }
            is Text -> {
                rawString
            }
        }
    }
}