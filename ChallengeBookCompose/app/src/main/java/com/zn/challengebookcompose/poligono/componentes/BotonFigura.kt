package com.zn.challengebookcompose.poligono.componentes

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.white

@Composable
fun BotonFigura(
    //icono (en este caso la imagen de la figura)
    icon: Painter,
    activeBackgroundColor: Color,
    modifier: Modifier = Modifier,
    isToggled: Boolean,
    onClick: () -> Unit = {},
) {
    val backgroundColor = if (isToggled) activeBackgroundColor else card_background_color

    IconButton(
        onClick = onClick,
        modifier = modifier.background(color = backgroundColor)
    ){
        Icon(
            painter = icon,
            contentDescription = null,
            tint = white,
        )
    }
}