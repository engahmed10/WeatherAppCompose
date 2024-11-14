package com.example.weatherapp.openweather.ui

import com.example.weatherapp.openweather.data.response.WeatherResponseDto

sealed class OpenWeatherState {
    object Loading : OpenWeatherState()
    data class Success(val data: WeatherResponseDto?) : OpenWeatherState()
    data class Error(val error: String) : OpenWeatherState()
}