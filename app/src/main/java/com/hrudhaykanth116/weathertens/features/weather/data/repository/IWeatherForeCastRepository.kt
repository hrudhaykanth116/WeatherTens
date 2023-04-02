package com.hrudhaykanth116.weathertens.features.weather.data.repository

import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.features.weather.data.models.WeatherForeCastResponse

interface IWeatherForeCastRepository {

    suspend fun getDailyWeatherForeCast(latitude: String, longitude: String): DataResult<WeatherForeCastResponse>

}