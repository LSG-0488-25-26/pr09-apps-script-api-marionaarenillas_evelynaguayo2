package com.example.netflixapp.data.network

import com.example.netflixapp.data.model.NetflixTitle
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class InsertRequest(
    val api_key: String,
    val action: String = "insert",
    val show_id: String,
    val type: String,
    val title: String,
    val director: String,
    val country: String,
    val release_year: Int,
    val rating: String,
    val duration: String,
    val listed_in: String,
    val description: String
)

data class InsertResponse(
    val success: Boolean? = null,
    val message: String? = null,
    val error: String? = null
)

interface ApiService {

    @GET("macros/s/AKfycbxZ-CN8CrodYQF5ndTonzuWgwo82Z7dValc4riq84VdC-KA86rJKJCROMdYum1tqqI_dQ/exec")
    suspend fun getAllTitles(
        @Query("action") action: String = "getAll",
        @Query("api_key") apiKey: String
    ): List<NetflixTitle>

    @GET("macros/s/AKfycbxZ-CN8CrodYQF5ndTonzuWgwo82Z7dValc4riq84VdC-KA86rJKJCROMdYum1tqqI_dQ/exec")
    suspend fun getTitlesByType(
        @Query("action") action: String = "getByType",
        @Query("type") type: String,
        @Query("api_key") apiKey: String
    ): List<NetflixTitle>

    @GET("macros/s/AKfycbxZ-CN8CrodYQF5ndTonzuWgwo82Z7dValc4riq84VdC-KA86rJKJCROMdYum1tqqI_dQ/exec")
    suspend fun getTitleById(
        @Query("action") action: String = "getById",
        @Query("id") id: String,
        @Query("api_key") apiKey: String
    ): NetflixTitle

    @POST("macros/s/AKfycbxZ-CN8CrodYQF5ndTonzuWgwo82Z7dValc4riq84VdC-KA86rJKJCROMdYum1tqqI_dQ/exec")
    suspend fun insertTitle(
        @Body request: InsertRequest
    ): Response<InsertResponse>
}