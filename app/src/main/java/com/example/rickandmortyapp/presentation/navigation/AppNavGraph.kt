package com.example.rickandmortyapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.rickandmortyapp.domain.entity.Character
import kotlin.reflect.typeOf

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    characterListContent: @Composable () -> Unit,
    characterDetailContent: @Composable (Character) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = CharacterListRoute
    ) {
        composable<CharacterListRoute> {
            characterListContent()
        }

        composable<CharacterDetailRoute>(
            typeMap = mapOf(
                typeOf<Character>() to Character.CharacterType
            )
        ) {
            val arguments = it.toRoute<CharacterDetailRoute>()
            characterDetailContent(arguments.character)
        }
    }
}