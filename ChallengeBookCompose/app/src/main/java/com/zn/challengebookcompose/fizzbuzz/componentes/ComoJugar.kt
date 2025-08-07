package com.zn.challengebookcompose.fizzbuzz.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
fun ComoJugar() {

    Column(modifier = Modifier.fillMaxSize().background(background_color)){
        Text(
            text = "Múltiplos de 3 por la palabra \"fizz\".\n" +
                    "Múltiplos de 5 por la palabra \"buzz\".\n" +
                    "Múltiplos de 3 y de 5 a la vez por la palabra \"fizzbuzz\"",
            color = white,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 40.dp)
        )
    }


}