package com.zn.challengebookcompose.challengebook2.pokemon.componentes


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.secondary_color

@Composable
fun BotonEleccionPokemon(
    icon: String,
    modifier: Modifier = Modifier,
    isToggled: Boolean,
    onClick: () -> Unit = {},
    borderColor: Color = Color.Black,
    borderWidth: Dp = 2.dp
) {
    val backgroundColor = if (isToggled) secondary_color else card_background_color
    val shape = RoundedCornerShape(8.dp)

    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(color = backgroundColor, shape = shape)
            .border(
                border = BorderStroke(borderWidth, borderColor),
                shape = shape
            )
            .clip(shape)
            .fillMaxSize()
    ) {
        AsyncImage(
            model = icon,
            contentDescription = null,
            modifier = Modifier.padding(5.dp).fillMaxSize()
        )
    }
}

fun extractPokemonId(url: String): Int {
    val segments = url.split("/")
    return segments[segments.size - 2].toInt()
}

