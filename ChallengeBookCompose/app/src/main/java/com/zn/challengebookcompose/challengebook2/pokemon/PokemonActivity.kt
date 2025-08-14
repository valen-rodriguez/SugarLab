package com.zn.challengebookcompose.challengebook2.pokemon

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.challengebook2.Menu2Activity
import com.zn.challengebookcompose.challengebook2.pokemon.batalla.PokemonBatallaActivity
import com.zn.challengebookcompose.challengebook2.pokemon.models.*
import com.zn.challengebookcompose.challengebook2.pokemon.componentes.BotonEleccionPokemon
import com.zn.challengebookcompose.challengebook2.pokemon.componentes.extractPokemonId
import com.zn.challengebookcompose.challengebook2.pokemon.pokeapi.PokeapiService
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://pokeapi.co/api/v2/"

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

class PokemonActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ObtenerDatos()
        }
    }
}


@Composable
fun ObtenerDatos() {

    var listaPokemon by remember { mutableStateOf<List<PokemonApi>>(emptyList()) }
    //eleccion actual para que puedan elegir sin cambiar de pantalla
    var eleccionJugadorActual by remember { mutableStateOf<PokemonBatalla?>(null) }

    val service: PokeapiService = retrofit.create(PokeapiService::class.java)

    service.obtenerListaPokemon()?.enqueue(object : Callback<PokemonRespuesta?> {
        override fun onResponse(
            call: Call<PokemonRespuesta?>,
            response: Response<PokemonRespuesta?>
        ) {
            if (response.isSuccessful) {
                listaPokemon = response.body()?.results ?: emptyList() //lista de resultados
            }
        }

        override fun onFailure(call: Call<PokemonRespuesta?>, t: Throwable) {
            Log.e("API_Error", "Error al cargar la lista: ${t.message}")
        }
    })


    fun cargarPokemonDetalles(pokemonApi: PokemonApi) {
        service.obtenerPokemonDetalle(pokemonApi.url)?.enqueue(object : Callback<PokemonDetalle?> {
            override fun onResponse(
                call: Call<PokemonDetalle?>,
                response: Response<PokemonDetalle?>
            ) {
                if (response.isSuccessful) {
                    val pokemonDetalle = response.body()
                    pokemonDetalle?.let {
                        val ataque =
                            it.stats.firstOrNull { stat -> stat.stat.name == "attack" }?.base_stat
                                ?: 0
                        val defensa =
                            it.stats.firstOrNull { stat -> stat.stat.name == "defense" }?.base_stat
                                ?: 0
                        val tipoName = it.types.firstOrNull()?.type?.name ?: "unknown"
                        val imageUrl = it.sprites.front_default ?: ""

                        val tipo = try {
                            Tipo.valueOf(tipoName.uppercase())
                        } catch (_: IllegalArgumentException) {
                            Tipo.UNKNOWN
                        }

                        eleccionJugadorActual = PokemonBatalla(
                            nombre = it.name,
                            tipo = tipo,
                            ataque = ataque,
                            defensa = defensa,
                            imagenUrl = imageUrl
                        )
                        Log.d("Pokemon", "Pokémon cargado: ${eleccionJugadorActual?.nombre}")
                    }
                }
            }

            override fun onFailure(call: Call<PokemonDetalle?>, t: Throwable) {
                Log.e("API_Error", "Error al cargar detalles: ${t.message}")
            }
        })
    }

    val resetearEleccion = {
        eleccionJugadorActual = null
    }

    ViewContainer(
        pokemons = listaPokemon,
        eleccionJugadorActual = eleccionJugadorActual,
        onPokemonSelected = { selected -> cargarPokemonDetalles(selected) },
        onResetSelection = resetearEleccion
    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ViewContainer(
    pokemons: List<PokemonApi>,
    eleccionJugadorActual: PokemonBatalla?,
    onPokemonSelected: (PokemonApi) -> Unit,
    onResetSelection: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            BarraSuperior("Pokemon", card_background_color, onHomeClick = {
                val intent = Intent(context, Menu2Activity::class.java)
                context.startActivity(intent)
            })
        },
        content = {
            ContentPokemon(
                pokemonList = pokemons,
                eleccionJugadorActual = eleccionJugadorActual,
                onPokemonSelected = onPokemonSelected,
                onResetSelection = onResetSelection

            )
        }
    )
}


@Composable
private fun ContentPokemon(
    pokemonList: List<PokemonApi>,
    eleccionJugadorActual: PokemonBatalla?,
    onPokemonSelected: (PokemonApi) -> Unit,
    onResetSelection: () -> Unit
) {

    val context = LocalContext.current

    //eleccion de los jugadores
    var eleccionJugador1 by remember { mutableStateOf<PokemonBatalla?>(null) }
    var eleccionJugador2 by remember { mutableStateOf<PokemonBatalla?>(null) }

    var textoInicio by remember { mutableStateOf("Jugador 1: ¡Elegí tu pokemon!") }


    val columnas = GridCells.Fixed(3)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(10.dp, top = 100.dp, end = 10.dp)
    ) {
        LazyVerticalGrid(
            columns = columnas,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 70.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = textoInicio,
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 25.dp)
                )
                ElevatedButton(
                    onClick = {

                        if (textoInicio == "Jugador 1: ¡Elegí tu pokemon!") {
                            eleccionJugador1 = eleccionJugadorActual
                            onResetSelection()
                            textoInicio = "Jugador 2: ¡Elegí tu pokemon!"
                        } else {
                            eleccionJugador2 = eleccionJugadorActual

                            val pokemon1 = eleccionJugador1
                            val pokemon2 = eleccionJugador2

                            val intent = Intent(context, PokemonBatallaActivity::class.java)
                            intent.putExtra("jugador1", pokemon1)
                            intent.putExtra("jugador2", pokemon2)
                            context.startActivity(intent)

                        }

                    },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = secondary_color,
                        contentColor = card_background_color
                    ),
                    enabled = eleccionJugadorActual != null,
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 40.dp)
                        .fillMaxWidth()
                ) {
                    Text("Elegir Pokemon")
                }
            }



            items(pokemonList) { pokemon ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    val pokemonId = extractPokemonId(pokemon.url)
                    val imageUrl =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"

                    Text(
                        text = pokemon.name.replaceFirstChar { pokemon.name[0].uppercase() },
                        color = white,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )

                    BotonEleccionPokemon(
                        icon = imageUrl,
                        isToggled = eleccionJugadorActual?.nombre == pokemon.name,
                        onClick = { onPokemonSelected(pokemon) },
                        modifier = Modifier.height(100.dp)
                    )

                    if (eleccionJugadorActual?.nombre == pokemon.name) {
                        eleccionJugadorActual.let {
                            Text(
                                text = "Ataque: ${it.ataque}\n" +
                                        "Defensa: ${it.defensa}\n" +
                                        "Tipo: ${it.tipo}",
                                color = white,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


