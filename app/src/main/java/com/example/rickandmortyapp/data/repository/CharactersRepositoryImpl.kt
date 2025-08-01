package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.network.api.ApiService
import com.example.rickandmortyapp.data.network.dto.ResultDto
import com.example.rickandmortyapp.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRepository {

    override suspend fun getAllCharacters(): ResultDto {
        return apiService.getAllCharacters()
    }
}