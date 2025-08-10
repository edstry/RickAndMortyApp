package com.example.rickandmortyapp.domain.entity

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.rickandmortyapp.data.local.model.CharacterDbModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
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

    companion object {
        val CharacterType = object : NavType<Character>(false) {
            override fun put(
                bundle: SavedState,
                key: String,
                value: Character
            ) {
                bundle.putString(key, Json.encodeToString(value))
            }

            override fun get(
                bundle: SavedState,
                key: String
            ): Character? {
                return Json.decodeFromString(bundle.getString(key) ?: return null)
            }

            override fun parseValue(value: String): Character {
                return Json.decodeFromString(Uri.decode(value))
            }

            override fun serializeAsValue(value: Character): String {
                return Uri.encode(Json.encodeToString(value))
            }
        }
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
