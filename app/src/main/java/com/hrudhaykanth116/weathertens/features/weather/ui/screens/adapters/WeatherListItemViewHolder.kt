package com.hrudhaykanth116.weathertens.features.weather.ui.screens.adapters

import androidx.recyclerview.widget.RecyclerView
import com.hrudhaykanth116.weathertens.databinding.ListItemWeatherBinding
import com.hrudhaykanth116.weathertens.features.weather.domain.models.WeatherListItemUIState

class WeatherListItemViewHolder(
    private val listItemWeatherBinding: ListItemWeatherBinding
) : RecyclerView.ViewHolder(listItemWeatherBinding.root) {

    fun bind(
        weatherListItemUIState: WeatherListItemUIState,
        eventListener: (ForeCastListAdapter.ItemEvent) -> Unit
    ) {
        listItemWeatherBinding.day.text = weatherListItemUIState.day
        listItemWeatherBinding.weather.text = weatherListItemUIState.weather
        listItemWeatherBinding.minTemp.text = "Min temp: ${weatherListItemUIState.minTemp}℃"
        listItemWeatherBinding.maxTemp.text = "Max temp: ${weatherListItemUIState.maxTemp}℃"


        listItemWeatherBinding.root.setOnClickListener {
            eventListener(ForeCastListAdapter.ItemEvent.Clicked(weatherListItemUIState))
        }
    }

}