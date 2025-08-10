package com.example.rickandmortyapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapp.domain.entity.Character

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateToCharacterDetail(character: Character) {
        navHostController.navigate(CharacterDetailRoute(character = character))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}