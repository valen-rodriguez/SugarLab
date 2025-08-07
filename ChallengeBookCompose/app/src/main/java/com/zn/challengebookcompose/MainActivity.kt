package com.zn.challengebookcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zn.challengebookcompose.anagrama.AnagramaActivity
import com.zn.challengebookcompose.fizzbuzz.FizzBuzzActivity
import com.zn.challengebookcompose.poligono.PoligonoActivity
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperiorMenu
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BotonMenu


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            ViewContainer(
                onFizzbuzz = { context.startActivity(Intent(context, FizzBuzzActivity::class.java)) },
                onPoligono = { context.startActivity(Intent(context, PoligonoActivity::class.java)) },
                onAnagrama = { context.startActivity(Intent(context, AnagramaActivity::class.java)) }

            )
        }
    }
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun ViewContainer(
    onFizzbuzz:() -> Unit,
    onPoligono:() -> Unit,
    onAnagrama:() -> Unit
) {
    Scaffold(
        topBar = {
            BarraSuperiorMenu(
                titulo = "ChallengeBook",
                backgroundColor = card_background_color
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(background_color),
            horizontalAlignment = Alignment.CenterHorizontally // Para centrar el botón horizontalmente
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                BotonMenu(
                    texto = "FizzBuzz",
                    color = card_background_color,
                    onClick = onFizzbuzz,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Anagrama",
                    color = card_background_color,
                    onClick = onAnagrama,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Poligono",
                    color = card_background_color,
                    onClick = onPoligono,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Anagrama",
                    color = card_background_color,
                    onClick = onAnagrama,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Anagrama",
                    color = card_background_color,
                    onClick = onAnagrama,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Anagrama",
                    color = card_background_color,
                    onClick = onAnagrama,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                Spacer(modifier = Modifier.height(16.dp)) // Espacio inferior
            }
        }
    }
}



