package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.domain.repository.CharactersRepository
import javax.inject.Inject

class LoadNextDataUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke() {
        repository.loadNextData()
    }
}