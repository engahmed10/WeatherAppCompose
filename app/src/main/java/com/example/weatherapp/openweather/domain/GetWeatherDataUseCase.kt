package com.example.weatherapp.openweather.domain

import com.example.weatherapp.WorldWeather.data.repository.WorldWeatherRepositoryImpl
import com.example.weatherapp.openweather.data.response.WeatherResponseDto
import com.example.weatherapp.openweather.ui.OpenWeatherState
import javax.inject.Inject

class GetWeatherDataUseCase @Inject
constructor(private val repository: WorldWeatherRepositoryImpl) {
    suspend operator fun invoke(cityName: String): OpenWeatherState {
        val response = repository.getWeather(cityName)
        if (response.isSuccessful) {
            response.body()?.let {
                return OpenWeatherState.Success(it)
            }.also {
                return OpenWeatherState.Error(response.message())
            }
        } else {
            return OpenWeatherState.Error("Failed to fetch weather data " + response.message())
        }
        // Handle other potential exceptions here
        return OpenWeatherState.Success(
            WeatherResponseDto(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        )
    }
}