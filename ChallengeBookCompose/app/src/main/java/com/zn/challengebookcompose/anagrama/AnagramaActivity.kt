package com.zn.challengebookcompose.anagrama

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zn.challengebookcompose.anagrama.componentes.CardEsAnagrama
import com.zn.challengebookcompose.poligono.PoligonoActivity
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BotonMenu

class AnagramaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewContainer()
        }
    }
}

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ViewContainer() {
    val context = LocalContext.current
    Scaffold(
        topBar = { BarraSuperior("Anagrama", card_background_color) },
        content = {
            ContentAnagrama(
                onPoligono = { context.startActivity(Intent(context, PoligonoActivity::class.java)) }
            )
        }
    )
}

@Composable
private fun ContentAnagrama(
    onPoligono: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(start = 20.dp, end = 20.dp, top = 150.dp, bottom = 1.dp)
    ) {
        item {
            CardEsAnagrama()
            BotonMenu(
                texto = "Siguiente Ejercicio",
                color = card_background_color,
                onClick = onPoligono,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 40.dp),
            )
        }
    }
}