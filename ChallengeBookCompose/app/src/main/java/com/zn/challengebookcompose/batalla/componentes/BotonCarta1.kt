package com.zn.challengebookcompose.batalla.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.zn.challengebookcompose.ui.theme.card_background_color

@Composable
fun BotonCarta1(
    icon: Painter,
    modifier: Modifier = Modifier,
    isToggled: Boolean,
    onClick: () -> Unit = {},
    activeBackgroundColor: Color,
) {
    val backgroundColor = if (isToggled) activeBackgroundColor else card_background_color

    IconButton(
        onClick = onClick,
        modifier = modifier.clip(RoundedCornerShape(5.dp)).background(color = backgroundColor).fillMaxSize()
    ){
        Image(
            painter = icon,
            contentDescription = null,
            modifier = modifier.padding( 5.dp)
        )
    }
}