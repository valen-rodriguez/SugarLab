package com.zn.challengebookcompose.carrera.componentes

import android.media.Image
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zn.challengebookcompose.R
import com.zn.challengebookcompose.ui.theme.card_background_color

@Composable
fun CardCarrera(random: Int) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp)) //redondea columnas
            .background(card_background_color)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = card_background_color),
            modifier = Modifier.size(width = 300.dp, height = 200.dp)
        ) {


            val imageResourceId: Int = if (random == 0) {
                R.drawable.pista // Esto es un Int (ID del recurso)
            } else {
                R.drawable.valla // Esto también es un Int
            }

            // Un estado para controlar si la imagen es visible.
            var isImageVisible by remember { mutableStateOf(false) }

            // Cambia el estado a 'true' para que la imagen aparezca con la animación
            LaunchedEffect(Unit) {
                isImageVisible = true
            }
            AnimatedVisibility(
                visible = isImageVisible,
                enter = fadeIn(animationSpec = tween(durationMillis = 1000)), // 1000ms de duración
            ) {
                Image(
                    painter = painterResource(id = imageResourceId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}