package com.example.rickandmortyapp.presentation.character_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(error: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = error,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}