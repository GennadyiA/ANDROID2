package com.example.android2.utils

import com.example.android2.model.FactDTO
import com.example.android2.model.Weather
import com.example.android2.model.WeatherDTO
import com.example.android2.model.getDefaultCity

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(Weather(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.icon))
}

