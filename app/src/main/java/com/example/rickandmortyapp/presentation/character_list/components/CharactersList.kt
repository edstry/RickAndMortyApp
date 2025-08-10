package com.example.rickandmortyapp.presentation.character_list.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyapp.domain.entity.Character
import com.example.rickandmortyapp.presentation.character_list.CharacterListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersList(
    characters: List<Character>,
    viewModel: CharacterListViewModel,
    isSearching: Boolean,
    nextDataIsLoading: Boolean
) {
    val searchText by viewModel.searchText.collectAsState()
    Log.d("dataawdawd", nextDataIsLoading.toString())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = searchText,
            onValueChange = {
                viewModel.onSearchTextChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") }
        )
        if(isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                columns = GridCells.Fixed(2)
            ) {

                items(characters) {
                    CharacterCard(character = it)
                }
                item(span = { GridItemSpan(2) }) {
                    if (nextDataIsLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(color = Color.Gray)
                        }
                    } else {
                        SideEffect {
                            viewModel.loadNextCharacters()
                        }
                    }
                }
            }
        }
    }
}