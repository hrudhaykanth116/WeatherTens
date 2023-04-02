package com.hrudhaykanth116.weathertens.features.auth.ui.models

import android.graphics.Bitmap
import androidx.compose.ui.text.input.TextFieldValue

sealed interface SignUpFormEvent {
    data class ProfileImageChanged(val imgBitmap: Bitmap?): SignUpFormEvent
    data class EmailChanged(val email: TextFieldValue): SignUpFormEvent
    data class PasswordChanged(val password: TextFieldValue): SignUpFormEvent
    data class ReEnteredPasswordChanged(val password: TextFieldValue): SignUpFormEvent
    data class UserNameChanged(val userName: TextFieldValue): SignUpFormEvent
    data class BioChanged(val bio: TextFieldValue): SignUpFormEvent
    object Submit: SignUpFormEvent
}