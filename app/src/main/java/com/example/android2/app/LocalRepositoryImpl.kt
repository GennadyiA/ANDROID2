package com.example.android2.app

import com.example.android2.room.HistoryDao
import com.example.android2.model.Weather
import com.example.android2.utils.convertHistoryEntityToWeather
import com.example.android2.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) :
    LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }
    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}
