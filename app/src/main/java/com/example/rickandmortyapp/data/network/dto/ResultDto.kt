package com.example.rickandmortyapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class ResultDto(

    @SerializedName("info")
    val infoDto: InfoDto,

    @SerializedName("results")
    val resultDto: List<CharacterDto>
)