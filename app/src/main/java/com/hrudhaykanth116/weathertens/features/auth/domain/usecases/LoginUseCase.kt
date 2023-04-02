package com.hrudhaykanth116.weathertens.features.auth.domain.usecases

import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.common.data.models.UIText
import com.hrudhaykanth116.weathertens.features.auth.data.models.LoginRequest
import com.hrudhaykanth116.weathertens.features.auth.data.models.LoginResult
import com.hrudhaykanth116.weathertens.features.auth.data.repository.IAuthRepository
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginUIState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {

    suspend operator fun invoke(loginUIState: LoginUIState): LoginUIState {

        val email = loginUIState.email
        val password = loginUIState.password

        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            return loginUIState.copy(
                emailErrorMessage = UIText.Text("Email cannot be empty"),
                passwordErrorMessage = UIText.Text("Password cannot be empty")
            )
        }

        val loginResult: DataResult<LoginResult> = authRepository.login(
            LoginRequest(
                email, password
            )
        )

        return when (loginResult) {
            is DataResult.Error -> {
                loginUIState.copy(
                    userMessage = loginResult.uiMessage
                )
            }
            is DataResult.Success -> {
                loginUIState.copy(
                    isLoggedIn = true
                )
            }
        }

    }

}