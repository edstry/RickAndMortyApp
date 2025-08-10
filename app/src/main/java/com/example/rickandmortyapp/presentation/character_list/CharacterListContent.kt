package com.example.rickandmortyapp.presentation.character_list

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortyapp.presentation.character_list.components.CharactersList
import com.example.rickandmortyapp.presentation.character_list.components.LoadingScreen

@Composable
fun CharacterListContent(
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state by viewModel.state

    val characterListForFilter by viewModel.characterListForFilter.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val isLoadingNext by viewModel.isLoading.collectAsState()

    if(state.isLoading) {
        LoadingScreen()
    }
    if(state.success) {
        CharactersList(
            characters = characterListForFilter,
            viewModel = viewModel,
            isSearching = isSearching,
            nextDataIsLoading = isLoadingNext
        )
    }
    if(state.error.isNotBlank()) {
        Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()
        CharactersList(
            characters = characterListForFilter,
            viewModel = viewModel,
            isSearching = isSearching,
            nextDataIsLoading = isLoadingNext
        )
    }
}