package com.example.rickandmortyapp.domain.entity

data class Character(
    val id: Int,
    val name: String,
    val gender: String,
    val species: String,
    val status: String,
    val imageUrl: String
)
