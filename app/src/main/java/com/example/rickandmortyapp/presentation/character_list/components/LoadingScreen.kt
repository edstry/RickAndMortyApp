package com.example.rickandmortyapp.presentation.character_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.rickandmortyapp.presentation.ui.theme.BgUsersColor
import com.example.rickandmortyapp.presentation.ui.theme.Grey40

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BgUsersColor)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center),
            color = Color.White
        )
    }
}