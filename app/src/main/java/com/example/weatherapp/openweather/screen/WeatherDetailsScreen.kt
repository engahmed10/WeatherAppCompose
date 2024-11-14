package com.example.weatherapp.openweather.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.openweather.ui.OpenWeatherState
import com.example.weatherapp.openweather.ui.WorldWeatherViewModel


@Composable
fun WeatherDetailsScreen(
    navController: NavHostController, modifier: Modifier, cityName: String,
    viewModel: WorldWeatherViewModel = hiltViewModel<WorldWeatherViewModel>()
) {

    val result = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getWeatherData(cityName)
    }

    when (val state = result.value) {
        is OpenWeatherState.Error -> {
             //TODOO handele error
        }
        OpenWeatherState.Loading -> {

        }
        is OpenWeatherState.Success -> {
            state.data?.weather?.forEach {
                LazyColumn {
                    item {
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.LightGray
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            onClick = {
                                //when you click on weather card it will go to details , i didn't  added new
                                // details  but same card details was added  with navigation process
                                //with new nav page
                                navController.navigate("weather_screen" + "?cityName=${cityName}")
                            }
                        ) {
                            Text(text = it.main, color = Color.Black)
                            Text(it.description, color = Color.Black)
                            Image(
                                painter =
                                rememberAsyncImagePainter("https://openweathermap.org/img/wn/${it.icon}@2x.png"),
                                contentDescription = "Icon",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .width(40.dp)
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(2.dp))
                            )
                            Text(it.id.toString(), color = Color.Black)
                        }
                    }
                }
            }
        }
    }

}