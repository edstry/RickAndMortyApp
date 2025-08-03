package com.example.rickandmortyapp.presentation.character_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.Utils.common.Resource
import com.example.rickandmortyapp.domain.entity.Character
import com.example.rickandmortyapp.domain.usecase.AddCharacterToDbUseCase
import com.example.rickandmortyapp.domain.usecase.ClearCharactersFromDbUseCase
import com.example.rickandmortyapp.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortyapp.domain.usecase.GetCharactersFromDbUseCase
import com.example.rickandmortyapp.domain.usecase.LoadNextDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase,
    private val addCharacterToDbUseCase: AddCharacterToDbUseCase,
    private val clearCharactersFromDbUseCase: ClearCharactersFromDbUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CharacterListState>(CharacterListState.Initial)
    private var charactersList = listOf<Character>()
    val state: StateFlow<CharacterListState>
        get() = _state.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getAllCharactersUseCase()
            .onEach { result ->
            when (result) {
                is Resource.Error<*> -> {
                    _state.value = CharacterListState.ErrorLoading(
                        result.message ?: "An unexpected error occured"
                    )
                    loadCharactersFromDatabase()
                }

                is Resource.Loading<*> -> {
                    _state.value = CharacterListState.Loading
                }

                is Resource.Success<*> -> {
                    clearCharacterListFromDatabase()
                    _state.value = CharacterListState.SuccessLoaded(
                        characters = result.data ?: emptyList()
                    )
                    charactersList = result.data ?: emptyList()
                    charactersList.forEach {
                        addCharacterToDatabase(it)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun addCharacterToDatabase(character: Character) {
        viewModelScope.launch {
            addCharacterToDbUseCase(character)
        }
    }

    private fun clearCharacterListFromDatabase() {
        viewModelScope.launch {
            clearCharactersFromDbUseCase()
        }
    }

    fun loadCharactersFromDatabase() {
        viewModelScope.launch {
            getCharactersFromDbUseCase().collect {
                _state.value = CharacterListState.SuccessLoaded(
                    characters = it
                )
            }
        }
    }

    fun loadNextCharacters() {
        if(charactersList.isNotEmpty()) {
            viewModelScope.launch {
                _state.emit(
                    CharacterListState.SuccessLoaded(
                        charactersList,
                        true
                    )
                )
                delay(500)
                loadNextDataUseCase()
            }
        }
    }
}

fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
    return merge(this, another)
}