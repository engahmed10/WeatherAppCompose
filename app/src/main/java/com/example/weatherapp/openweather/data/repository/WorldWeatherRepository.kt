package com.example.weatherapp.openweather.data.repository

import com.example.weatherapp.openweather.data.response.WeatherResponseDto
import retrofit2.Response

interface WorldWeatherRepository {
    suspend fun getWeather(cityName: String): Response<WeatherResponseDto>
}