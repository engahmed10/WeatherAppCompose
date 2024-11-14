package com.example.weatherapp.openweather.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weatherapp.openweather.ui.WorldWeatherViewModel

@Composable
fun WeatherSearchScreen(
    navController: NavHostController,
    modifier: Modifier, viewModel: WorldWeatherViewModel = hiltViewModel<WorldWeatherViewModel>()
) {

    val searchClicked = viewModel.searchClicked.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = viewModel.searchText.value,
            onValueChange = { viewModel.searchText.value = it },
            label = { Text("Enter City Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.getWeatherData(viewModel.cityName.value)
                viewModel.onSearchClicked()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enter The City Name, please")
        }
        if (searchClicked.value){
            WeatherDetailsScreen(navController, modifier, viewModel.cityName.value)
        }
    }

}