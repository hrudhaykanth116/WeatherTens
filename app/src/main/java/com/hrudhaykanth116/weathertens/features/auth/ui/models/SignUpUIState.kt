package com.hrudhaykanth116.weathertens.features.auth.ui.models

import android.graphics.Bitmap
import androidx.compose.ui.text.input.TextFieldValue
import com.hrudhaykanth116.weathertens.common.data.models.UIText
import com.hrudhaykanth116.weathertens.features.auth.domain.usecases.ValidateEmailUseCase
import com.hrudhaykanth116.weathertens.features.auth.domain.usecases.ValidatePasswordUseCase

data class SignUpUIState(
    val imgBitmap: Bitmap? = null,

    val emailTextFieldValue: TextFieldValue = TextFieldValue(),
    val emailError: UIText? = null,

    val passwordTextFieldValue: TextFieldValue = TextFieldValue(),
    val passwordError: UIText? = null,

    val repeatedPassword: TextFieldValue = TextFieldValue(),
    val repeatedPasswordError: UIText? = null,

    val userName: TextFieldValue = TextFieldValue(),
    val userNameError: UIText? = null,

    val bio: TextFieldValue = TextFieldValue(),
    val bioError: UIText? = null,

    val userMessage: UIText? = null,
    val isSignedUp: Boolean = false,
) {

    fun getStateAfterValidation(
        validateEmailUseCase: ValidateEmailUseCase,
        validatePasswordUseCase: ValidatePasswordUseCase
    ): SignUpUIState {
        return copy(
            emailError = validateEmailUseCase(emailTextFieldValue.text),
            passwordError = validatePasswordUseCase(passwordTextFieldValue.text),
            repeatedPasswordError = if (passwordTextFieldValue.text != repeatedPassword.text) UIText.Text(
                "Passwords do not match"
            ) else null,
            userNameError = if (passwordTextFieldValue.text.isBlank()) UIText.Text("Passwords do not match") else null,
            bioError = if (bio.text.isBlank()) UIText.Text("Bio cannot be empty") else null,
        )
    }

    fun containsError(): Boolean {
        return emailError == null
                && passwordError == null
                && repeatedPasswordError == null
                && userNameError == null
                && bioError == null
    }

}