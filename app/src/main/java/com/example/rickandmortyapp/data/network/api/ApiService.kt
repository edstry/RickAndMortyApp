package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.dto.ResultDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(): ResultDto

    @GET("character/")
    suspend fun getAllCharacters(
        @Query("page") page: String
    ): ResultDto
}