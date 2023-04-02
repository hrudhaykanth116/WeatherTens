package com.hrudhaykanth116.weathertens.features.auth.domain.models

import com.hrudhaykanth116.weathertens.common.data.models.UIText

sealed interface LoginUIEvent{
    data class Login(val email: String?, val password: String?): LoginUIEvent
    data class UserMessageShown(val uiText: UIText): LoginUIEvent
}