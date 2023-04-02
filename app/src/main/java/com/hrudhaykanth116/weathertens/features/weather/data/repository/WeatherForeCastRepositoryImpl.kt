package com.hrudhaykanth116.weathertens.features.weather.data.repository

import com.hrudhaykanth116.weathertens.common.data.models.DataResult
import com.hrudhaykanth116.weathertens.features.weather.data.datasources.local.WeatherForeCastLocalDataSource
import com.hrudhaykanth116.weathertens.features.weather.data.datasources.remote.WeatherForeCastRemoteDataSource
import com.hrudhaykanth116.weathertens.features.weather.data.models.WeatherForeCastResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherForeCastRepositoryImpl @Inject constructor(
    // TODO: Implement abstraction depending on the use case
    private val weatherForeCastRemoteDataSource: WeatherForeCastRemoteDataSource,
    private val weatherForeCastLocalDataSource: WeatherForeCastLocalDataSource
) : IWeatherForeCastRepository {

    private val dispatcher = Dispatchers.IO

    override suspend fun getDailyWeatherForeCast(
        latitude: String, longitude: String
    ): DataResult<WeatherForeCastResponse> = withContext(dispatcher) {
        weatherForeCastRemoteDataSource.getWeatherForeCast(latitude, longitude)
    }


}