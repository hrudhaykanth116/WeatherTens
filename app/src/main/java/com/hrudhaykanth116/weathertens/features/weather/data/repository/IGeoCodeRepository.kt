package com.hrudhaykanth116.weathertens.features.weather.data.repository

import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.features.weather.data.models.GetLocationInfoResponseItem

interface IGeoCodeRepository{

    suspend fun getLocationInfo(location: String): DataResult<List<GetLocationInfoResponseItem>>

}