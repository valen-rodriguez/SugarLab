package com.zn.challengebookcompose.challengebook2.pokemon.batalla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.zn.challengebookcompose.challengebook2.Menu2Activity
import com.zn.challengebookcompose.challengebook2.pokemon.models.PokemonBatalla
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior



class PokemonBatallaActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            // Recuperamos los objetos Parcelable de forma segura
            val pokemon1 = intent.getParcelableExtra("jugador1", PokemonBatalla::class.java)
            val pokemon2 = intent.getParcelableExtra("jugador2", PokemonBatalla::class.java)


            ViewContainer(pokemon1, pokemon2)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ViewContainer(pokemon1: PokemonBatalla?, pokemon2: PokemonBatalla?) {
    val context = LocalContext.current
    Scaffold(
        topBar = { BarraSuperior("Pokemon", card_background_color,onHomeClick = {
            val intent = Intent(context, Menu2Activity::class.java)
            context.startActivity(intent)
        }) },
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(background_color)
            ){
                ContentBatalla(pokemon1, pokemon2)
            }
        }
    )
}


@Composable
private fun ContentBatalla(
    pokemon1: PokemonBatalla?, pokemon2: PokemonBatalla?
){

    var resultText by remember {mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (pokemon1 != null && pokemon2 != null) {
        // Carta del Jugador 1
        PokemonCard(
            pokemon = pokemon1,
            title = "Pokemon Jugador 1"
        )

        // Espacio entre las cartas
        Spacer(modifier = Modifier.height(32.dp))

        // Carta del Jugador 2
        PokemonCard(
            pokemon = pokemon2,
            title = "Pokemon Jugador 2"
        )


        val ganador = quienGana(pokemon1, pokemon2)

        //ganador[0] = jugador 1 o 2
        //ganador[1] = pokemon
        //ganador[2] = daño hecho
        //ganador[3] = efectividad

            if (ganador.contains(",")){
                ganador.split(",")

                resultText = "El ganador es el Jugador ${ganador[0]}!\n" +
                        "Su ${ganador[1].uppercase()} hizo ${ganador[2]} puntos de daño\n" +
                        "Su efectividad fue ${ganador[3]} contra ${pokemon2.nombre.uppercase()}"
            }else{
                resultText = "Empate"
            }

        Text(
            text = resultText,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(top = 10.dp)
        )
        } else {
            Text(
                text = "No se recibieron los datos de los Pokémon",
                color = Color.Red,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PokemonCard(pokemon: PokemonBatalla?, title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(card_background_color, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la carta
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Imagen del Pokémon
        AsyncImage(
            model = pokemon?.imagenUrl,
            contentDescription = "Imagen de ${pokemon?.nombre}",
            modifier = Modifier.size(100.dp)
        )

        // Espacio entre la imagen y el nombre
        Spacer(modifier = Modifier.height(8.dp))

        //Nombre del Pokémon

        pokemon?.nombre?.replaceFirstChar { it.uppercase() }?.let {
            Text(
                text = it,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
        }

        //Nombre del Pokémon
        Text(
            text = "Ataque: ${pokemon?.ataque}\n" +
                    "Defensa: ${pokemon?.defensa}\n" +
                    "Tipo: ${pokemon?.tipo}",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center
        )
    }
}