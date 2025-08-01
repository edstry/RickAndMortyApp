package com.example.rickandmortyapp.presentation.character_list

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterListContent(
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    when(val currentState = state.value) {
        is CharacterListState.ErrorLoading -> {
            Log.d("TAG_CHARACTER_LIST", currentState.message)
        }
        CharacterListState.Loading -> {
            Log.d("TAG_CHARACTER_LIST", currentState.toString())
        }
        is CharacterListState.SuccessLoaded -> {
            Log.d("TAG_CHARACTER_LIST", currentState.characters.toString())
        }
        CharacterListState.Initial -> { Log.d("TAG_CHARACTER_LIST", currentState.toString()) }
    }


}