package com.zn.challengebookcompose.batalla.componentes


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.white

@Composable
fun BotonCarta(
    icon: Painter,
    modifier: Modifier = Modifier,
    isToggled: Boolean,
    onClick: () -> Unit = {},
    activeBackgroundColor: Color,
    borderColor: Color = Color.Black,
    borderWidth: Dp = 2.dp
) {
    val backgroundColor = if (isToggled) activeBackgroundColor else card_background_color
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
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.padding(5.dp).fillMaxSize()
        )
    }
}