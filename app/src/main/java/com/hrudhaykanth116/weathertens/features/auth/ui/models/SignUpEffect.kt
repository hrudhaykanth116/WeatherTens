package com.hrudhaykanth116.weathertens.features.auth.ui.models

import com.hrudhaykanth116.weathertens.common.data.models.UIText

sealed class SignUpEffect{
    data class Success(val message: UIText? = null): SignUpEffect()
    data class Error(val message: UIText? = null): SignUpEffect()
}