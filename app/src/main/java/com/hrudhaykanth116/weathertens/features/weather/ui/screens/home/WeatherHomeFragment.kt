package com.hrudhaykanth116.weathertens.features.weather.ui.screens.home

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import coil.load
import com.hrudhaykanth116.weathertens.R
import com.hrudhaykanth116.weathertens.common.udf.UDFFragment
import com.hrudhaykanth116.weathertens.databinding.FragmentWeatherHomeScreenBinding
import com.hrudhaykanth116.weathertens.features.weather.domain.models.WeatherHomeScreenEffect
import com.hrudhaykanth116.weathertens.features.weather.domain.models.WeatherHomeScreenEvent
import com.hrudhaykanth116.weathertens.features.weather.domain.models.WeatherHomeScreenUIState
import com.hrudhaykanth116.weathertens.features.weather.ui.screens.adapters.ForeCastListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherHomeFragment :
    UDFFragment<WeatherHomeScreenUIState, WeatherHomeScreenEvent, WeatherHomeScreenEffect, FragmentWeatherHomeScreenBinding>(
        R.layout.fragment_weather_home_screen
    ) {

    override val viewModel: WeatherHomeScreenViewModel by viewModels()

    private val forecastListAdapter: ForeCastListAdapter by lazy {
        ForeCastListAdapter() {
            // TODO: Refactor
            when (it) {
                is ForeCastListAdapter.ItemEvent.Clicked -> {
                    val action =
                        WeatherHomeFragmentDirections.actionWeatherHomeScreenToWeatherDetailsFragment(
                            it.weatherListItemUIState
                        )
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun initViews() {
        binding.weatherList.adapter = forecastListAdapter
        binding.weatherList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.searchIcon.setOnClickListener {
            processEvent(WeatherHomeScreenEvent.Refresh(binding.location.text?.toString()))
        }
        binding.logoutBtn.setOnClickListener {
            processEvent(WeatherHomeScreenEvent.LogOut)

        }
    }

    override fun processNewEffect(effect: WeatherHomeScreenEffect) {

    }

    override fun processNewState(state: WeatherHomeScreenUIState) {

        if (state.isLoggedOut) {
            // findNavController().navigate(
            //     WeatherHomeFragmentDirections.actionWeatherHomeScreenToAuthNavGraph()
            // )
        }

        state.errorMessage?.let {
            Toast.makeText(requireContext(), it.getText(requireContext()), Toast.LENGTH_SHORT)
                .show()
            processEvent(WeatherHomeScreenEvent.UserMessageShown(it))
        }

        binding.userName.text = state.userName
        binding.bio.text = state.userBio
        binding.profileImage.load(state.userProfileImageUrl, builder = {
            placeholder(R.drawable.profile_icon)
        })
        binding.progressBar.isVisible = state.isLoading

        forecastListAdapter.submitList(
            state.weatherListItemsUIState
        )

    }
}