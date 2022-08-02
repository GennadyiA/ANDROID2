package com.example.android2.app

import com.example.android2.model.Weather

interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}