package com.example.rickandmortyapp.presentation.character_list.components

data class CharactersState(
    val isLoading: Boolean = false,
    val error: String = "",
    val success: Boolean = false
)
