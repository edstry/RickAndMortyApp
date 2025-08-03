package com.example.rickandmortyapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyapp.domain.entity.Character

@Entity(tableName = "character_list")
data class CharacterDbModel(
    @PrimaryKey val id: Int,
    val name: String,
    val gender: String,
    val species: String,
    val status: String,
    val imageUrl: String
)

fun CharacterDbModel.toEntity(): Character {
    return Character(
        id = id,
        name = name,
        gender = gender,
        species = species,
        status = status,
        imageUrl = imageUrl
    )
}

fun List<CharacterDbModel>.toEntities(): List<Character> {
    return map { it.toEntity() }
}
