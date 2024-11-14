package com.example.weatherapp.openweather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.openweather.screen.WeatherDetailsScreen
import com.example.weatherapp.openweather.screen.WeatherSearchScreen

@Composable
fun WeatherNavigation(navController: NavHostController, modifier: Modifier) {

    NavHost(
        navController = navController,
        startDestination = WeatherNavigationRoute.WeatherSearchScreen.route
    ) {
        composable(WeatherNavigationRoute.WeatherSearchScreen.route) {

            WeatherSearchScreen(navController, modifier)
        }
        composable(WeatherNavigationRoute.WeatherDetailsScreen.route+"?cityName={cityName}") {
            val cityName= it.arguments?.getString("cityName")
            if (cityName==null) return@composable
            WeatherDetailsScreen(navController, modifier, cityName)
        }
    }


}