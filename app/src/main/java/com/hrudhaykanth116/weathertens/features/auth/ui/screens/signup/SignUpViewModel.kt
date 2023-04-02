package com.hrudhaykanth116.weathertens.features.auth.ui.screens.signup

import androidx.lifecycle.viewModelScope
import com.hrudhaykanth116.weathertens.common.udf.UDFViewModel
import com.hrudhaykanth116.weathertens.features.auth.domain.usecases.SignUpUseCase
import com.hrudhaykanth116.weathertens.features.auth.ui.models.SignUpEffect
import com.hrudhaykanth116.weathertens.features.auth.ui.models.SignUpFormEvent
import com.hrudhaykanth116.weathertens.features.auth.ui.models.SignUpUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signupUseCase: SignUpUseCase,
): UDFViewModel<SignUpUIState, SignUpFormEvent, SignUpEffect >(
    SignUpUIState()
) {

    override fun processEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.EmailChanged -> {
                setState {
                    copy(emailTextFieldValue = event.email)
                }
            }
            is SignUpFormEvent.PasswordChanged -> {
                setState {
                    copy(passwordTextFieldValue = event.password)
                }
            }
            is SignUpFormEvent.ReEnteredPasswordChanged -> {
                setState {
                    copy(repeatedPassword = event.password)
                }
            }
            SignUpFormEvent.Submit -> {
                viewModelScope.launch {
                    val newUIState = signupUseCase(state)
                    setState {
                        newUIState
                    }
                }
            }
            is SignUpFormEvent.ProfileImageChanged -> {
                setState {
                    copy(imgBitmap = event.imgBitmap)
                }
            }
            is SignUpFormEvent.BioChanged -> {
                setState {
                    copy(bio = event.bio)
                }
            }
            is SignUpFormEvent.UserNameChanged -> {
                setState {
                    copy(userName = event.userName)
                }
            }
        }
    }


}