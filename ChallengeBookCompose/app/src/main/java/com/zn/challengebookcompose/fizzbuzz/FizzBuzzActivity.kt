package com.zn.challengebookcompose.fizzbuzz

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
import com.zn.challengebookcompose.anagrama.AnagramaActivity
import com.zn.challengebookcompose.fizzbuzz.componentes.RangeSlider1To100
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BotonMenu
import com.zn.challengebookcompose.ui.theme.componentesgenerales.TextoNormal

class FizzBuzzActivity : ComponentActivity() {
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
        topBar = { BarraSuperior("FizzBuzz", card_background_color) },
        content = { ContentFizzBuzz(
            onAnagrama = { context.startActivity(Intent(context, AnagramaActivity::class.java)) }
        ) }
    )
}

@Composable
fun ContentFizzBuzz(
    onAnagrama: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding( 10.dp, top = 100.dp, end = 10.dp)
    ) {
        item {
            TextoNormal("Múltiplos de 3 por la palabra \"fizz\".\n" +
                    "Múltiplos de 5 por la palabra \"buzz\".\n" +
                    "Múltiplos de 3 y de 5 a la vez por la palabra \"fizzbuzz\"",
                Modifier.padding(top = 10.dp))

            RangeSlider1To100()
            BotonMenu(
                texto = "Siguiente Ejercicio",
                color = card_background_color,
                onClick = onAnagrama,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 40.dp),
                )
        }
    }
}





