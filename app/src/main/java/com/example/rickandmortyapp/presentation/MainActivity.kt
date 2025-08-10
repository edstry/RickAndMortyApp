package com.example.rickandmortyapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.rickandmortyapp.data.network.api.ApiFactory
import com.example.rickandmortyapp.presentation.character_detail.CharacterDetailContent
import com.example.rickandmortyapp.presentation.character_list.CharacterListContent
import com.example.rickandmortyapp.presentation.navigation.AppNavGraph
import com.example.rickandmortyapp.presentation.navigation.rememberNavigationState
import com.example.rickandmortyapp.presentation.ui.theme.RickAndMortyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            RickAndMortyAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    AppNavGraph(
        navHostController = navigationState.navHostController,
        characterListContent = {
            CharacterListContent(
                onItemClickListener = {
                    navigationState.navigateToCharacterDetail(character = it)
                }
            )
        },
        characterDetailContent = { character ->

            CharacterDetailContent(
                character = character,
                onBackClick = {
                    navigationState.navHostController.popBackStack()
                }
            )
        }
    )
}