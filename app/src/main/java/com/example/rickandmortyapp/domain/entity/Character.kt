package com.example.rickandmortyapp.domain.entity

import com.example.rickandmortyapp.data.local.model.CharacterDbModel

data class Character(
    val id: Int,
    val name: String,
    val gender: String,
    val species: String,
    val status: String,
    val imageUrl: String
)

fun Character.toDbModel(): CharacterDbModel {
    return CharacterDbModel(
        id = id,
        name = name,
        gender = gender,
        species = species,
        status = status,
        imageUrl = imageUrl
    )
}
