package com.hrudhaykanth116.weathertens.features.auth.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.hrudhaykanth116.weathertens.common.udf.UDFViewModel
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginEffect
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginUIEvent
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginUIState
import com.hrudhaykanth116.weathertens.features.auth.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : UDFViewModel<LoginUIState, LoginUIEvent, LoginEffect>(
    LoginUIState()
) {
    override fun processEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.Login -> {
                viewModelScope.launch {
                    setState {
                        copy(isLoading = true)
                    }
                    val newUIState: LoginUIState = loginUseCase(
                        state.copy(
                            email = event.email,
                            password = event.password,
                            isLoading = false,
                        )
                    )
                    setState { newUIState }
                }
            }
            is LoginUIEvent.UserMessageShown -> {
                if (state.userMessage == event.uiText) {
                    setState {
                        copy(
                            userMessage = null,
                        )
                    }
                }

            }
        }
    }

}