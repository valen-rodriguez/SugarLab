package com.zn.challengebookcompose.conjuntos.componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.gray
import com.zn.challengebookcompose.ui.theme.white

@Composable
fun Arreglos (
    texto: String,
    value: String,
    onValueChange: (String) -> Unit
){
    Text(
        text = texto,
        color = white,
        fontFamily = FontFamily.Monospace,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 20.dp)
    )

    //primer array ingresada por el usuario
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = "Ej: 1,hola,3,que,sugar,lab") },
        colors = TextFieldDefaults.colors(
            // Colores en estado "no enfocado"
            unfocusedContainerColor = card_background_color,
            unfocusedIndicatorColor = white,
            unfocusedLabelColor = white,
            unfocusedTextColor = white,

            // Colores en estado "enfocado"
            focusedContainerColor = card_background_color,
            focusedIndicatorColor = white,
            focusedLabelColor = white,
            focusedTextColor = white,

            // Color del placeholder
            unfocusedPlaceholderColor = gray,
            focusedPlaceholderColor = gray,
        ),
        modifier = Modifier.padding(top = 20.dp)
    )
}