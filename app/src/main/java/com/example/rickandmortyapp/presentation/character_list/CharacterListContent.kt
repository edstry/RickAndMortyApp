package com.example.rickandmortyapp.presentation.character_list

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortyapp.presentation.character_list.components.CharactersList
import com.example.rickandmortyapp.presentation.character_list.components.LoadingScreen

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
            LoadingScreen()
        }
        is CharacterListState.SuccessLoaded -> {
            Log.d("TAG_CHARACTER_LIST", currentState.characters.toString())
            CharactersList(characters = currentState.characters)
        }
        CharacterListState.Initial -> {}
    }


}