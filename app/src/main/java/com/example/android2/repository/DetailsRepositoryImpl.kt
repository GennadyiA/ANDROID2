package com.example.android2.repository

import com.example.android2.model.WeatherDTO
import retrofit2.Callback


class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DetailsRepository {
    override fun getWeatherDetailsFromServer(lat: Double, lon: Double, callback: Callback<WeatherDTO>) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)
    }
}

