package com.example.weatherapp.openweather.data.remoteDataSource

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.openweather.data.api.WorldWeatherApiService
import com.example.weatherapp.openweather.data.response.WeatherResponseDto
import retrofit2.Response
import javax.inject.Inject

class WorldWeatherRemoteDataSource @Inject constructor(
    private val apiService: WorldWeatherApiService
) {
    suspend fun getWeather(cityName: String): Response<WeatherResponseDto> =
        apiService.getWeather(cityName, BuildConfig.MY_API_KEY)
}
