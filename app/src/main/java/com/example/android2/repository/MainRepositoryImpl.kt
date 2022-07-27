package com.example.android2.repository

import com.example.android2.model.Weather
import com.example.android2.model.getRussianCities
import com.example.android2.model.getWorldCities

class MainRepositoryImpl : MainRepository {
    override fun getWeatherFromServer() = Weather()
    override fun getWeatherFromLocalStorageRus() = getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}