package com.example.weatherapp.openweather.data.api

import com.example.weatherapp.openweather.data.response.WeatherResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WorldWeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Response<WeatherResponseDto>


}