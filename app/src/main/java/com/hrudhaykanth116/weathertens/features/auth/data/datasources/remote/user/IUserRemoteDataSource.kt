package com.hrudhaykanth116.weathertens.features.auth.data.datasources.remote.user

import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.features.auth.data.models.UserData

interface IUserRemoteDataSource {

    suspend fun getUserData(): DataResult<UserData>

}