package com.example.rickandmortyapp.data.network.dto

import com.example.rickandmortyapp.domain.entity.Character
import com.google.gson.annotations.SerializedName

data class CharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    @SerializedName("location")
    val locationDto: LocationDto,
    val name: String,
    @SerializedName("origin")
    val originDto: OriginDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun CharacterDto.toEntities(): Character {
    return Character(
        id = id,
        name = name,
        gender = gender,
        species = species,
        status = status,
        imageUrl = image
    )
}