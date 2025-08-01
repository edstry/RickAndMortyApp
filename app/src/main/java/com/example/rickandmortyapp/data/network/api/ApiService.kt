package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.dto.ResultDto
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(): ResultDto
}