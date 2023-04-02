package com.hrudhaykanth116.weathertens.features.weather.domain.usecases

import com.hrudhaykanth116.weathertens.common.utils.date.DateTimeUtils
import com.hrudhaykanth116.weathertens.features.weather.data.models.WeatherForeCastResponse
import com.hrudhaykanth116.weathertens.features.weather.domain.models.WeatherListItemUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseForeCastDtoUseCase @Inject constructor(
    private val dateTimeUtils: DateTimeUtils
) {

    // TODO: Use DI to inject this
    private val dispatcher = Dispatchers.Default

    suspend operator fun invoke(data: WeatherForeCastResponse): List<WeatherListItemUIState> = withContext(dispatcher){
        val dailyData = data.daily ?: return@withContext listOf()

        val weatherListItemUIStates = mutableListOf<WeatherListItemUIState>()

        dailyData.forEach { dayDataNullable ->
            dayDataNullable?.let { dayData ->
                weatherListItemUIStates.add(
                    getWeatherListItemUIState(dayData)
                )
            }

        }

        return@withContext weatherListItemUIStates
    }

    private fun getWeatherListItemUIState(dayData: WeatherForeCastResponse.Daily) =
        WeatherListItemUIState(
            // Temperature in Kelvin
            // Wind speed in meter/sec
            // Pressure hPa
            // Clouds %
            // rain mm/h
            // HUmidity %
            // Assuming non null values.
            dateTimeUtils.getDateFromSecs(dayData.dt!!),
            weather = dayData.weather?.firstOrNull()?.main.orEmpty(),
            humidity = dayData.humidity?.toString().orEmpty(),
            rain = dayData.rain?.toString().orEmpty(),
            sunrise = dayData.sunrise?.toString().orEmpty(),
            sunset = dayData.sunset?.toString().orEmpty(),
            maxTemp = dayData.temp?.max?.toString().orEmpty(),
            minTemp = dayData.temp?.min?.toString().orEmpty(),
            windSpeed = dayData.windSpeed?.toString().orEmpty(),
            clouds = dayData.clouds?.toString().orEmpty()
        )

}