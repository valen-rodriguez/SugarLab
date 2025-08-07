package com.zn.challengebookcompose.ui.theme.componentesgenerales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.white

@Composable
fun TextoNormal(texto: String, modifier: Modifier) {

    Column(modifier = Modifier.fillMaxSize().background(background_color)){
        Text(
            text = texto,
            color = white,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = modifier
        )
    }


}