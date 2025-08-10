package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersFromDbUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke() = repository.getCharactersFromDatabase
}