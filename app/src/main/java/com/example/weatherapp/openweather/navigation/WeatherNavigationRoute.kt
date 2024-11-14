package com.example.weatherapp.openweather.navigation

sealed class WeatherNavigationRoute(val route: String) {
    object WeatherSearchScreen : WeatherNavigationRoute("weather_search_screen")
    object WeatherDetailsScreen : WeatherNavigationRoute("weather_screen")
}

