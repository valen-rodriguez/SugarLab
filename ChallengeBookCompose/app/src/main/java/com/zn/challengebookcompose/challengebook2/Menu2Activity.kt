package com.zn.challengebookcompose.challengebook2

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
import com.zn.challengebookcompose.batalla.BatallaActivity
import com.zn.challengebookcompose.carrera.CarreraActivity
import com.zn.challengebookcompose.challengebook2.calculadora.CalculadoraActivity
import com.zn.challengebookcompose.challengebook2.pokemon.PokemonActivity
import com.zn.challengebookcompose.challengebook2.zodiacochino.ZodiacoChinoActivity
import com.zn.challengebookcompose.conjuntos.ConjuntosActivity
import com.zn.challengebookcompose.poligono.PoligonoActivity
import com.zn.challengebookcompose.rps.RpsActivity
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperiorMenu
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BotonMenu


class Menu2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            ViewContainer(
                onCalculadora = { context.startActivity(Intent(context, CalculadoraActivity::class.java)) },
                onSexagenario = { context.startActivity(Intent(context, ZodiacoChinoActivity::class.java)) },
                onPokemon = { context.startActivity(Intent(context, PokemonActivity::class.java)) },
                onConjuntos = { context.startActivity(Intent(context, ConjuntosActivity::class.java)) },
                onCarrera = { context.startActivity(Intent(context, CarreraActivity::class.java))},
                onBatalla = { context.startActivity(Intent(context, BatallaActivity::class.java))},
                onRps = { context.startActivity(Intent(context, RpsActivity::class.java)) }


            )
        }
    }
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun ViewContainer(
    onCalculadora:() -> Unit,
    onSexagenario:() -> Unit,
    onCarrera:() -> Unit,
    onConjuntos:() -> Unit,
    onRps:() -> Unit,
    onBatalla:() -> Unit,
    onPokemon:() -> Unit
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
                    texto = "Calculadora",
                    color = card_background_color,
                    onClick = onCalculadora,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Pokemon",
                    color = card_background_color,
                    onClick = onPokemon,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Sexagenario Chino",
                    color = card_background_color,
                    onClick = onSexagenario,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Carrera de Obstáculos",
                    color = card_background_color,
                    onClick = onCarrera,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Conjuntos",
                    color = card_background_color,
                    onClick = onConjuntos,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Piedra, Papel o Tijeras",
                    color = card_background_color,
                    onClick = onRps,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                BotonMenu(
                    texto = "Clash Royale",
                    color = card_background_color,
                    onClick = onBatalla,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                )

                Spacer(modifier = Modifier.height(16.dp)) // Espacio inferior
            }
        }
    }
}



