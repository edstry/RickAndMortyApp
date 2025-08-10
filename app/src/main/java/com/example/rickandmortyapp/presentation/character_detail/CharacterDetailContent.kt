package com.example.rickandmortyapp.presentation.character_detail

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.entity.Character
import com.example.rickandmortyapp.presentation.ui.theme.BgUsersColor
import com.example.rickandmortyapp.presentation.ui.theme.LabelColor

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailContent(
    character: Character,
    onBackClick: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BgUsersColor,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "ID: ${character.id}",
                        fontSize = 16.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(
                        onClick = { onBackClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White
                ),
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp),
        ) {
            item {
                GlideImage(
                    contentScale = ContentScale.Crop,
                    model = character.imageUrl,
                    contentDescription = null,
                )
            }
            item {
                Column {
                    Text(
                        text = stringResource(R.string.label_name),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = LabelColor
                    )
                    Text(
                        text = character.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
            item {
                Column {
                    Text(
                        text = stringResource(R.string.label_gender),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = LabelColor
                    )
                    Text(
                        text = character.gender,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
            item {
                Column {
                    Text(
                        text = stringResource(R.string.label_status),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = LabelColor
                    )
                    Text(
                        text = character.status,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
            item {
                Column {
                    Text(
                        text = stringResource(R.string.lable_species),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = LabelColor
                    )
                    Text(
                        text = character.species,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }

}