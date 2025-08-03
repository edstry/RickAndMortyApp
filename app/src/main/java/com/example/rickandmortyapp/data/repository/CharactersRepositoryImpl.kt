package com.example.rickandmortyapp.data.repository

import android.util.Log
import com.example.rickandmortyapp.Utils.common.Resource
import com.example.rickandmortyapp.data.network.api.ApiService
import com.example.rickandmortyapp.data.network.dto.toEntities
import com.example.rickandmortyapp.domain.entity.Character
import com.example.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRepository {

    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)
    private var nextData: String? = null
    private val _charactersCached = mutableListOf<Character>()
    val charactersCached: List<Character>
        get() = _charactersCached.toList()
    private val loadedCharacters = flow {
        nextDataNeededEvents.emit(Unit)
        nextDataNeededEvents.collect {
            try {
                val startFrom = nextData
                if(startFrom == null && charactersCached.isNotEmpty()) {
                    emit(Resource.Success(charactersCached))
                    return@collect
                }

                val response = if (startFrom == null) {
                    emit(Resource.Loading())
                    apiService.getAllCharacters()
                } else {
                    val page = startFrom.split("=").last()
                    apiService.getAllCharacters(page = page)
                }

                nextData = response.infoDto.next
                val characters = response.resultDto.map { it.toEntities() }
                _charactersCached.addAll(characters)
                emit(Resource.Success(data = charactersCached))

            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error("Could`t reach server. Check your internet connection"))
            }
        }
    }

    override fun getAllCharacters() = loadedCharacters

    override suspend fun loadNextData() {
        nextDataNeededEvents.emit(Unit)
    }
}