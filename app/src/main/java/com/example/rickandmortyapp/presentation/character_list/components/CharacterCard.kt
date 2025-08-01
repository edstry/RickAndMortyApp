package com.example.rickandmortyapp.presentation.character_list.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rickandmortyapp.domain.entity.Character
import com.example.rickandmortyapp.presentation.ui.theme.Grey40

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterCard(character: Character) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = Grey40),
        shape = RoundedCornerShape(15.dp),
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .sizeIn(minHeight = 260.dp)
                .clickable { },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                GlideImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    model = character.imageUrl,
                    contentDescription = null
                )
                Row(
                    modifier = Modifier
                        .size(70.dp, 23.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp))
                        .align(Alignment.BottomEnd)
                        .background(Color.Black),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(
                        modifier = Modifier.size(10.dp)
                            .weight(1f)
                    ) {
                        drawCircle(
                            color = Color.Red
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = character.status,
                        fontSize = 12.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = character.name)
            Text(text = "${character.gender} | ${character.species}")
        }
    }
}