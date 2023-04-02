package com.hrudhaykanth116.weathertens.features.auth.ui.screens.signup

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrudhaykanth116.weathertens.R
import com.hrudhaykanth116.weathertens.common.ui.components.AppFormButton
import com.hrudhaykanth116.weathertens.common.ui.components.AppFormInputText
import com.hrudhaykanth116.weathertens.common.ui.components.CenteredColumn
import com.hrudhaykanth116.weathertens.common.ui.components.CircularImage
import com.hrudhaykanth116.weathertens.common.ui.models.InputType
import com.hrudhaykanth116.weathertens.common.ui.models.TextFieldData
import com.hrudhaykanth116.weathertens.common.utils.compose.HandleEffect
import com.hrudhaykanth116.weathertens.common.utils.compose.MyPreview
import com.hrudhaykanth116.weathertens.features.auth.ui.models.SignUpEffect
import com.hrudhaykanth116.weathertens.features.auth.ui.models.SignUpFormEvent
import com.hrudhaykanth116.weathertens.features.auth.ui.models.SignUpUIState

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignedIn: () -> Unit = {},
) {

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        viewModel.processEvent(SignUpFormEvent.ProfileImageChanged(it))
    }

    if (state.isSignedUp) {
        onSignedIn()
    }

    HandleEffect(viewModel = viewModel) { effect ->
        when (effect) {
            is SignUpEffect.Success -> {
                // ToastHelper.showSuccessToast(context, effect.message)
            }
            is SignUpEffect.Error -> {
                // ToastHelper.showErrorToast(context,
                //     effect.message ?: UIText.StringRes(R.string.something_went_wrong)
                // )
            }
        }
    }

    SignUpScreenContent(
        state,
        onProfileClicked = {
            launcher.launch(null)
        },
        onEmailChanged = {
            viewModel.processEvent(SignUpFormEvent.EmailChanged(it))
        },
        onPasswordChanged = {
            viewModel.processEvent(SignUpFormEvent.PasswordChanged(it))
        },
        onReEnterPasswordChanged = {
            viewModel.processEvent(SignUpFormEvent.ReEnteredPasswordChanged(it))
        },
        onUserNameChanged = {
            viewModel.processEvent(SignUpFormEvent.UserNameChanged(it))
        },
        onBioChanged = {
            viewModel.processEvent(SignUpFormEvent.BioChanged(it))
        },

        ) {
        viewModel.processEvent(SignUpFormEvent.Submit)
    }
}

@MyPreview
@Composable
private fun SignUpScreenContent(
    state: SignUpUIState = SignUpUIState(),
    onProfileClicked: () -> Unit = {},
    onEmailChanged: (TextFieldValue) -> Unit = {},
    onPasswordChanged: (TextFieldValue) -> Unit = {},
    onReEnterPasswordChanged: (TextFieldValue) -> Unit = {},
    onUserNameChanged: (TextFieldValue) -> Unit = {},
    onBioChanged: (TextFieldValue) -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    CenteredColumn {

        state.userMessage?.let {
            // TODO: Show user message or use effects.
        }

        Row() {
            CircularImage(
                modifier = Modifier.size(100.dp),
                image = state.imgBitmap ?: R.drawable.profile_icon,
            ) {
                onProfileClicked()
            }
            Text(text = "Set display picture")
        }

        EmailTextField(state, onEmailChanged)
        PasswordTextField(state, onPasswordChanged)
        ReEnterPasswordTextField(state, onReEnterPasswordChanged)
        UserNameTextField(state, onUserNameChanged)
        BioTextField(state, onBioChanged)
        AppFormButton(btnText = "Sign up") { onSubmit() }
    }
}

@Composable
private fun UserNameTextField(
    state: SignUpUIState,
    onReEnterPasswordChange: (TextFieldValue) -> Unit
) {
    AppFormInputText(
        TextFieldData(
            hint = "Username",
            inputType = InputType.RegularInputType,
            inputValue = state.userName,
            error = state.userNameError?.getText()
        )
    ) { onReEnterPasswordChange(it) }
}

@Composable
private fun BioTextField(
    state: SignUpUIState,
    onReEnterPasswordChange: (TextFieldValue) -> Unit
) {
    AppFormInputText(
        TextFieldData(
            label = "A short bio",
            inputType = InputType.RegularInputType,
            inputValue = state.bio,
            error = state.bioError?.getText()
        )
    ) { onReEnterPasswordChange(it) }
}

@Composable
private fun ReEnterPasswordTextField(
    state: SignUpUIState,
    onReEnterPasswordChange: (TextFieldValue) -> Unit
) {
    AppFormInputText(
        TextFieldData(
            label = "ReEnter your password",
            inputType = InputType.PwdInputType,
            inputValue = state.repeatedPassword,
            error = state.repeatedPasswordError?.getText()
        )
    ) { onReEnterPasswordChange(it) }
}

@Composable
private fun PasswordTextField(
    state: SignUpUIState,
    onPasswordChanged: (TextFieldValue) -> Unit
) {
    AppFormInputText(
        TextFieldData(
            label = "Enter your password",
            inputType = InputType.PwdInputType,
            inputValue = state.passwordTextFieldValue,
            error = state.passwordError?.getText()
        )
    ) { onPasswordChanged(it) }
}

@Composable
private fun EmailTextField(
    state: SignUpUIState,
    onEmailChanged: (TextFieldValue) -> Unit
) {
    AppFormInputText(
        TextFieldData(
            label = "Enter your email",
            inputType = InputType.EmailInputType,
            inputValue = state.emailTextFieldValue,
            error = state.emailError?.getText()
        )
    ) { onEmailChanged(it) }
}