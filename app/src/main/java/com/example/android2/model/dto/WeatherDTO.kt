package com.example.android2.model.dto

data class WeatherDTO(
    val fact: FactDTO?
)
data class FactDTO(
    val temp: Int?,
    val feels_like: Int?,
    val condition: String?
)