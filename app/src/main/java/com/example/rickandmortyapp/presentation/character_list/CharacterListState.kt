package com.example.rickandmortyapp.presentation.character_list

import com.example.rickandmortyapp.domain.entity.Character

sealed interface CharacterListState {
    data class SuccessLoaded(
        val characters: List<Character>,
        val nextDataIsLoading: Boolean = false
    ): CharacterListState
    data class ErrorLoading(val message: String): CharacterListState
    data object Loading: CharacterListState
    data object Initial: CharacterListState
}