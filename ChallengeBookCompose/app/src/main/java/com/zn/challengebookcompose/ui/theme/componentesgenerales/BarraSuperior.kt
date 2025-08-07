package com.zn.challengebookcompose.ui.theme.componentesgenerales

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(titulo: String, backgroundColor: Color, showback: Boolean = true){

    val flechaVolver = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher //esto es para volver atras me da acceso al dispatcher del sistema android

    CenterAlignedTopAppBar(
        title = {
            Text(text = titulo, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, fontSize = 32.sp, color = white)
        },
        navigationIcon = {
            if (showback) {
                IconButton(onClick = {flechaVolver?.onBackPressed()}) { //aca llamo al dispatcher para volver
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor
        )
    )
}