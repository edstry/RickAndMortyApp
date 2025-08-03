package com.example.rickandmortyapp.Utils.di

import android.content.Context
import com.example.rickandmortyapp.data.local.db.CharacterDataBase
import com.example.rickandmortyapp.data.local.db.CharactersDao
import com.example.rickandmortyapp.data.network.api.ApiFactory
import com.example.rickandmortyapp.data.network.api.ApiService
import com.example.rickandmortyapp.data.repository.CharactersRepositoryImpl
import com.example.rickandmortyapp.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    companion object {
        @Provides
        @Singleton
        fun provideApiService(): ApiService = ApiFactory.apiService

        @Provides
        @Singleton
        fun provideCharactersRepository(apiService: ApiService): CharactersRepository {
            return CharactersRepositoryImpl(apiService)
        }

        @Provides
        fun provideCharactersDatabase(context: Context): CharacterDataBase {
            return CharacterDataBase.getInstance(context)
        }

        @Provides
        @Singleton
        fun provideCharactersDao(database: CharacterDataBase): CharactersDao {
            return database.charactersDao()
        }
    }


}