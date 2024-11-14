package com.example.weatherapp

import com.example.weatherapp.openweather.data.response.Weather
import com.example.weatherapp.openweather.data.response.WeatherResponseDto
import kotlinx.coroutines.withContext

import com.example.weatherapp.openweather.domain.GetWeatherDataUseCase
import com.example.weatherapp.openweather.ui.OpenWeatherState
import com.example.weatherapp.openweather.ui.WorldWeatherViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.io.IOException
import kotlin.String

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class WorldWeatherViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var weatherUseCase: GetWeatherDataUseCase

    @InjectMocks
    private lateinit var viewModel: WorldWeatherViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getWeatherData_WhenSuccess_ShouldReturnWeatherData`() = runTest(testDispatcher) {
        val city_Name = "London"
       val weatherData = WeatherResponseDto(
            base = "base",
            clouds = null,
            cod = 200,
            coord = null,
            dt = 123456789,
            id = 123,
            main = null,
            name = city_Name,
            sys = null,
            timezone = 3600,
            visibility = 10000,
            weather = listOf(Weather(
                description = "Sunny",
                icon = "icon_url",
                id = 4,
                main = 10.0.toString(),
            )),
            wind = null
        )

       // Mock the use case to return weather data
        whenever(weatherUseCase(city_Name)).thenReturn(
            OpenWeatherState.Success(weatherData)
        )


        viewModel.getWeatherData(city_Name)

        //  state is updated
        advanceUntilIdle()
        val state = viewModel.state.first()
        assertEquals(OpenWeatherState.Success(weatherData),state)
    }

    @Test
    fun `getWeatherData_WhenIOException_ShouldReturnErrorState`() = runTest(testDispatcher) {
        val cityName = "London"
        val exception = IOException("Connection error")

        // Mock the use case to throw IOException
        withContext(Dispatchers.IO) {
            throw exception
        }

        // Assert that the state is set to error
        val state = viewModel.state.first()
        assert(state is OpenWeatherState.Error)
    }

}
