package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.domain.entity.Character
import com.example.rickandmortyapp.domain.repository.CharactersRepository
import javax.inject.Inject

class AddCharacterToDbUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(character: Character) {
        repository.addCharacterToDatabase(character)
    }
}