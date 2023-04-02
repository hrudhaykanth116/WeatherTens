package com.hrudhaykanth116.weathertens.features.auth.data.repository.user

import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.features.auth.data.datasources.remote.user.IUserRemoteDataSource
import com.hrudhaykanth116.weathertens.features.auth.data.models.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: IUserRemoteDataSource
): IUserRepository {

    private val dispatcher = Dispatchers.IO

    override suspend fun getUserData(): DataResult<UserData> = withContext(dispatcher) {
        return@withContext remoteDataSource.getUserData()
    }
}