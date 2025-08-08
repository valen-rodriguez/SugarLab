package com.zn.challengebookcompose.conjuntos.componentes

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.white

@Composable
fun BotonComunDiferente(
    icon: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val backgroundColor = card_background_color

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