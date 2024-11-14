package com.example.weatherapp.openweather.di

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.openweather.data.api.WorldWeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorldWeatherModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WorldWeatherApiService {
        return retrofit.create(WorldWeatherApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}
