package com.example.weatherapp.WorldWeather.data.repository


import com.example.weatherapp.openweather.data.remoteDataSource.WorldWeatherRemoteDataSource
import com.example.weatherapp.openweather.data.repository.WorldWeatherRepository
import com.example.weatherapp.openweather.data.response.WeatherResponseDto
import retrofit2.Response
import javax.inject.Inject

class WorldWeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WorldWeatherRemoteDataSource,
) : WorldWeatherRepository {
    override suspend fun getWeather(cityName: String): Response<WeatherResponseDto> {
        return remoteDataSource.getWeather(cityName)
    }

}