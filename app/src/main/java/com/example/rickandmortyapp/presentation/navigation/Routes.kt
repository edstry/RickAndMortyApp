package com.example.rickandmortyapp.presentation.navigation

import com.example.rickandmortyapp.domain.entity.Character
import kotlinx.serialization.Serializable

@Serializable
data object CharacterListRoute

@Serializable
data class CharacterDetailRoute(val character: Character)