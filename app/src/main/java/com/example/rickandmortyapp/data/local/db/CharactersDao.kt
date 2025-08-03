package com.example.rickandmortyapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapp.data.local.model.CharacterDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Query("SELECT * FROM character_list")
    fun getCharactersFromDb(): Flow<List<CharacterDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacterToDb(characterDbModel: CharacterDbModel)

    @Query("DELETE FROM character_list")
    suspend fun clearCharacterDb()
}

