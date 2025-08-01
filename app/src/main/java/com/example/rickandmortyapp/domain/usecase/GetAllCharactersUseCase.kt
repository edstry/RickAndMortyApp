package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.Utils.common.Resource
import com.example.rickandmortyapp.data.network.dto.toEntities
import com.example.rickandmortyapp.domain.entity.Character
import com.example.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(): Flow<Resource<List<Character>>> = flow {
        try {
            emit(Resource.Loading())
            val character = repository.getAllCharacters().resultDto.map { it.toEntities() }
            emit(Resource.Success(character))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Could`t reach server. Check your internet connection"))
        }
    }
}