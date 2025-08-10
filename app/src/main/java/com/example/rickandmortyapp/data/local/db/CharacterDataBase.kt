package com.example.rickandmortyapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortyapp.data.local.model.CharacterDbModel

@Database(entities = [CharacterDbModel::class], version = 1, exportSchema = false)
abstract class CharacterDataBase: RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    companion object {
        private const val DB_NAME = "CharactersDatabase"
        private var INSTANCE: CharacterDataBase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): CharacterDataBase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = CharacterDataBase::class.java,
                    name = DB_NAME
                ).build()

                INSTANCE = database
                return database
            }
        }
    }
}