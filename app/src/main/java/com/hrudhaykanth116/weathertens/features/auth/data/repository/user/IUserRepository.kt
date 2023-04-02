package com.hrudhaykanth116.weathertens.features.auth.data.repository.user

import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.features.auth.data.models.UserData

interface IUserRepository {

    suspend fun getUserData(): DataResult<UserData>

}