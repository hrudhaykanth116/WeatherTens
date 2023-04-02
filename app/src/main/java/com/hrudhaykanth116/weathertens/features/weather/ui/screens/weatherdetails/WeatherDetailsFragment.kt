package com.hrudhaykanth116.weathertens.features.weather.ui.screens.weatherdetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hrudhaykanth116.weathertens.R
import com.hrudhaykanth116.weathertens.common.udf.UDFFragment
import dagger.hilt.android.AndroidEntryPoint
import com.hrudhaykanth116.weathertens.databinding.FragmentWeatherDetailsBinding as Binding
import com.hrudhaykanth116.weathertens.features.weather.domain.models.weatherdetails.WeatherDetailsScreenEffect as Effect
import com.hrudhaykanth116.weathertens.features.weather.domain.models.weatherdetails.WeatherDetailsScreenEvent as Event
import com.hrudhaykanth116.weathertens.features.weather.domain.models.weatherdetails.WeatherDetailsScreenUIState as State

@AndroidEntryPoint
class WeatherDetailsFragment : UDFFragment<State, Event, Effect, Binding>(
    R.layout.fragment_weather_details
) {

    override val viewModel: WeatherDetailsViewModel by viewModels()

    // TODO: Move data to viewModel savedstatehandle
    private val args: WeatherDetailsFragmentArgs by navArgs()


    override fun initViews() {
        val state = args.weatherData

        binding.humidity.text = state.humidity
    }

    override fun processNewEffect(effect: Effect) {

    }

    override fun processNewState(state: State) {

    }


}