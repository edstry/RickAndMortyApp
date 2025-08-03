package com.example.rickandmortyapp.presentation.character_list

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
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
            //ErrorScreen(error = currentState.message)
            Toast.makeText(
                LocalContext.current,
                currentState.message,
                Toast.LENGTH_LONG
            ).show()
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