package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.Utils.common.Resource
import com.example.rickandmortyapp.data.local.model.CharacterDbModel
import com.example.rickandmortyapp.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getAllCharacters(): Flow<Resource<List<Character>>>
    suspend fun loadNextData()
    suspend fun triggerLoadData()

    val getCharactersFromDatabase: Flow<List<Character>>
    suspend fun addCharacterToDatabase(character: Character)
    suspend fun clearCharactersFromDatabase()
}