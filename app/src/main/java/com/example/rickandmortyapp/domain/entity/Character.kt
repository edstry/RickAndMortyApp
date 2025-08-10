package com.example.rickandmortyapp.domain.entity

import com.example.rickandmortyapp.data.local.model.CharacterDbModel

data class Character(
    val id: Int,
    val name: String,
    val gender: String,
    val species: String,
    val status: String,
    val imageUrl: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        return gender.equals(query, ignoreCase = true) ||
                name.equals(query, ignoreCase = true) ||
                species.equals(query, ignoreCase = true) ||
                status.equals(query, ignoreCase = true)
    }
}

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
