package com.example.netflixapp.data.model

// Representa una fila del GoogleSheets
data class NetflixTitle(
    val show_id: String = "",
    val type: String = "",
    val title: String = "",
    val director: String = "",
    val country: String = "",
    val release_year: Int = 0,
    val rating: String = "",
    val duration: String = "",
    val listed_in: String = "",
    val description: String = ""
)