package com.example.android2.repository

import com.example.android2.model.WeatherDTO
import retrofit2.Callback


interface DetailsRepository {
    fun getWeatherDetailsFromServer(lat: Double, lon: Double, callback: Callback<WeatherDTO>)
}
