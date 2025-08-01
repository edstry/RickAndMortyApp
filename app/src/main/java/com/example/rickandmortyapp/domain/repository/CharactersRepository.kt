package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.data.network.dto.ResultDto

interface CharactersRepository {

    suspend fun getAllCharacters(): ResultDto
}