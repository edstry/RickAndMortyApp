package com.example.rickandmortyapp.presentation.character_list

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortyapp.presentation.character_list.components.CharactersList
import com.example.rickandmortyapp.presentation.character_list.components.ErrorScreen
import com.example.rickandmortyapp.presentation.character_list.components.LoadingScreen

@Composable
fun CharacterListContent(
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    when(val currentState = state.value) {
        is CharacterListState.ErrorLoading -> {
            ErrorScreen(error = currentState.message)
        }
        CharacterListState.Loading -> {
            LoadingScreen()
        }
        is CharacterListState.SuccessLoaded -> {
            CharactersList(
                characters = currentState.characters,
                viewModel = viewModel,
                currentState.nextDataIsLoading
            )
        }
        CharacterListState.Initial -> {}
    }


}