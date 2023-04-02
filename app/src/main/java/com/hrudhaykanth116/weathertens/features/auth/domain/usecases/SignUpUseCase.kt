package com.hrudhaykanth116.weathertens.features.auth.domain.usecases

import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.features.auth.data.models.SignUpRequest
import com.hrudhaykanth116.weathertens.features.auth.data.repository.IAuthRepository
import com.hrudhaykanth116.weathertens.features.auth.ui.models.SignUpUIState
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val authRepository: IAuthRepository
) {

    suspend operator fun invoke(signUpUIState: SignUpUIState): SignUpUIState {

        val newUIState = signUpUIState.getStateAfterValidation(
            validateEmailUseCase, validatePasswordUseCase
        )

        if (newUIState.containsError()) {
            return newUIState
        } else {
            val signUpResult = authRepository.signUp(
                SignUpRequest(
                    email = signUpUIState.emailTextFieldValue.text,
                    password = signUpUIState.passwordTextFieldValue.text,
                    userName = newUIState.userName.text,
                    imgBitmap = signUpUIState.imgBitmap,
                    bio = newUIState.bio.text,
                )
            )
            return when (signUpResult) {
                is DataResult.Error -> {
                    newUIState.copy(
                        userMessage = signUpResult.uiMessage
                    )
                }
                is DataResult.Success -> {
                    newUIState.copy(
                        isSignedUp = true
                    )
                }
            }
        }
    }
}