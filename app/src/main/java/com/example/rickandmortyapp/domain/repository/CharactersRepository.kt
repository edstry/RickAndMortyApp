package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.Utils.common.Resource
import com.example.rickandmortyapp.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getAllCharacters(): Flow<Resource<List<Character>>>
    suspend fun loadNextData()
}