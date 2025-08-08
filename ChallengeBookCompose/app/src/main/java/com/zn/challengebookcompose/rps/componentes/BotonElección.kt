package com.zn.challengebookcompose.rps.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white

@Composable
fun BotonEleccion(
    icon: Painter,
    modifier: Modifier = Modifier,
    isToggled: Boolean,
    onClick: () -> Unit = {},
) {
    val backgroundColor = if (isToggled) secondary_color else card_background_color

    IconButton(
        onClick = onClick,
        modifier = modifier.background(color = backgroundColor).size(70.dp).padding( 10.dp)
    ){
        Icon(
            painter = icon,
            contentDescription = null,
            tint = white,
        )
    }
}