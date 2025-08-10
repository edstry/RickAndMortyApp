@file:OptIn(FlowPreview::class)

package com.example.rickandmortyapp.presentation.character_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.Utils.common.Resource
import com.example.rickandmortyapp.domain.entity.Character
import com.example.rickandmortyapp.domain.usecase.AddCharacterToDbUseCase
import com.example.rickandmortyapp.domain.usecase.ClearCharactersFromDbUseCase
import com.example.rickandmortyapp.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortyapp.domain.usecase.GetCharactersFromDbUseCase
import com.example.rickandmortyapp.domain.usecase.LoadNextDataUseCase
import com.example.rickandmortyapp.domain.usecase.TriggerLoadDataUseCase
import com.example.rickandmortyapp.presentation.character_list.components.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase,
    private val addCharacterToDbUseCase: AddCharacterToDbUseCase,
    private val clearCharactersFromDbUseCase: ClearCharactersFromDbUseCase,
    private val triggerLoadDataUseCase: TriggerLoadDataUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CharactersState())
    val state: State<CharactersState>
        get() = _state

    private var charactersList = listOf<Character>()
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _isSearching = MutableStateFlow(false)
    val isSearching
        get() = _isSearching.asStateFlow()

    val isLoading = MutableStateFlow(false)

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private val _characterListForFilter = MutableStateFlow<List<Character>>(listOf<Character>())
    val characterListForFilter = _searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_characterListForFilter) { searchText, characters ->
            if (searchText.isBlank()) {
                characters
            } else {
                delay(1000)
                characters.filter {
                    it.doesMatchSearchQuery(searchText)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _characterListForFilter.value
        )


    init {
        getCharacters()
    }

    fun getCharacters() {
        getAllCharactersUseCase()
            .onEach { result ->
                when (result) {

                    is Resource.Error<*> -> {
                        _state.value = CharactersState(error = result.message ?: "An unexpected error occured")

                        loadCharactersFromDatabase()
                    }

                    is Resource.Loading<*> -> {
                        Log.d("awfawdasdwd", "23434")
                        _state.value = CharactersState(isLoading = true)
                    }

                    is Resource.Success<*> -> {
                        _state.value = CharactersState(success = true)

                        _characterListForFilter.value = result.data ?: emptyList()
                        clearCharacterListFromDatabase()
                        charactersList = result.data ?: emptyList()
                        charactersList.forEach {
                            addCharacterToDatabase(it)
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
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
                _characterListForFilter.value = it
                _state.value = CharactersState(
                    success = true
                )
            }
        }
    }

    fun loadNextCharacters() {
        if (charactersList.isNotEmpty()) {
            viewModelScope.launch {
                _characterListForFilter.value = charactersList
                isLoading.value = true
                delay(500)
                loadNextDataUseCase()
                isLoading.value = false
            }
        }
    }

    fun triggerLoadData() {
        viewModelScope.launch {
            triggerLoadDataUseCase()
        }
    }
}

fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
    return merge(this, another)
}