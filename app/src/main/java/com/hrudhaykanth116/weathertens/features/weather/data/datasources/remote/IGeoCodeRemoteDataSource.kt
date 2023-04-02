package com.hrudhaykanth116.weathertens.features.weather.data.datasources.remote

import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.features.weather.data.models.GetLocationInfoResponseItem

interface IGeoCodeRemoteDataSource {

    suspend fun getLocationInfo(location: String): DataResult<List<GetLocationInfoResponseItem>>

}