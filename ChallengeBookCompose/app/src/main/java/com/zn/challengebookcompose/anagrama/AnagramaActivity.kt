package com.zn.challengebookcompose.anagrama

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zn.challengebookcompose.anagrama.componentes.CardEsAnagrama
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.componentesgenerales.TextoNormal

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
            )
        }
    )
}

@Composable
fun ContentAnagrama() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding( 10.dp, top = 85.dp, end = 10.dp)
    ) {
        item {
            CardEsAnagrama()
        }
    }
}