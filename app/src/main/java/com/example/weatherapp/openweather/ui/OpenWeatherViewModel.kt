package com.example.weatherapp.openweather.ui
                    

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.openweather.domain.GetWeatherDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WorldWeatherViewModel @Inject constructor(private val weatherUseCase: GetWeatherDataUseCase): ViewModel() {

    private var _state = MutableStateFlow<OpenWeatherState>(OpenWeatherState.Loading)
    val state = _state.asStateFlow()

    val searchText = mutableStateOf("")
    private val _searchClicked = MutableStateFlow(false)
    val searchClicked = _searchClicked.asStateFlow()

    val cityName = mutableStateOf("")

    fun onSearchClicked() {
        cityName.value = searchText.value
        _searchClicked.value = true
    }

    fun getWeatherData(cityName: String) {
        _state.value = OpenWeatherState.Loading
        try {
            viewModelScope.launch(IO) {
                val weatherData = weatherUseCase(cityName)
                if (weatherData != null) {
                    _state.value = weatherData
                }
            }
        } catch (e: Exception) {
            _state.value = OpenWeatherState.Error(e.message ?: "An error occurred while ")
        }

    }
}
            