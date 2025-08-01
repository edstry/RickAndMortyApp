package com.example.rickandmortyapp.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.Utils.common.Resource
import com.example.rickandmortyapp.domain.usecase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CharacterListState>(CharacterListState.Initial)
    val state: StateFlow<CharacterListState>
        get() = _state.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getAllCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Error<*> -> {
                    _state.value = CharacterListState.ErrorLoading(
                        result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading<*> -> {
                    _state.value = CharacterListState.Loading
                }

                is Resource.Success<*> -> {
                    _state.value = CharacterListState.SuccessLoaded(
                        characters = result.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}