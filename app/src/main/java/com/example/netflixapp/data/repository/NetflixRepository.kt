package com.example.netflixapp.data.repository

import com.example.netflixapp.BuildConfig
import com.example.netflixapp.data.model.NetflixTitle
import com.example.netflixapp.data.network.InsertRequest
import com.example.netflixapp.data.network.InsertResponse
import com.example.netflixapp.data.network.RetrofitInstance
import retrofit2.Response

class NetflixRepository {

    suspend fun getAllTitles(): List<NetflixTitle> {
        return RetrofitInstance.api.getAllTitles(apiKey = BuildConfig.API_KEY)
    }

    suspend fun getTitlesByType(type: String): List<NetflixTitle> {
        return RetrofitInstance.api.getTitlesByType(
            type = type,
            apiKey = BuildConfig.API_KEY
        )
    }

    suspend fun getTitleById(id: String): NetflixTitle {
        return RetrofitInstance.api.getTitleById(
            id = id,
            apiKey = BuildConfig.API_KEY
        )
    }

    suspend fun insertTitle(title: NetflixTitle): Response<InsertResponse> {
        val request = InsertRequest(
            api_key = BuildConfig.API_KEY,
            show_id = title.show_id,
            type = title.type,
            title = title.title,
            director = title.director,
            country = title.country,
            release_year = title.release_year,
            rating = title.rating,
            duration = title.duration,
            listed_in = title.listed_in,
            description = title.description
        )

        return RetrofitInstance.api.insertTitle(request)
    }
}