package com.hrudhaykanth116.weathertens.features.auth.domain.models

import com.hrudhaykanth116.weathertens.common.data.models.UIText

data class LoginUIState(
    val email: String? = null,
    val password: String? = null,
    val emailErrorMessage: UIText? = null,
    val passwordErrorMessage: UIText? = null,
    val userMessage: UIText? = null,
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val shouldNavigateToSignUp: Boolean = false,
)