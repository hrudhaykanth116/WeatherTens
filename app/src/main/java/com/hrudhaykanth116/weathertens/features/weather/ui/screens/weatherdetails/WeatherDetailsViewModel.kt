package com.hrudhaykanth116.weathertens.features.weather.ui.screens.weatherdetails

import com.hrudhaykanth116.weathertens.common.udf.UDFViewModel
import com.hrudhaykanth116.weathertens.features.weather.domain.models.weatherdetails.WeatherDetailsScreenEffect
import com.hrudhaykanth116.weathertens.features.weather.domain.models.weatherdetails.WeatherDetailsScreenEvent
import com.hrudhaykanth116.weathertens.features.weather.domain.models.weatherdetails.WeatherDetailsScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(

) : UDFViewModel<WeatherDetailsScreenUIState, WeatherDetailsScreenEvent, WeatherDetailsScreenEffect>(
    WeatherDetailsScreenUIState()
) {
    override fun processEvent(event: WeatherDetailsScreenEvent) {

    }


}