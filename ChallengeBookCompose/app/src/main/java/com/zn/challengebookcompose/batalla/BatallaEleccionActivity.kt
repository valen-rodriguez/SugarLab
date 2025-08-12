package com.zn.challengebookcompose.batalla

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.MainActivity
import com.zn.challengebookcompose.R
import com.zn.challengebookcompose.batalla.carta.*
import com.zn.challengebookcompose.batalla.componentes.BotonCarta
import com.zn.challengebookcompose.batalla.componentes.batalla
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white

class BatallaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewContainer()
        }
    }
}

@Preview
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun ViewContainer() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            BarraSuperior("Clash Royale", card_background_color, onHomeClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            })
        },
        content = { ContentBatalla() }
    )
}


@Composable
fun ContentBatalla() {

    //quien juega
    var turnoJugador by remember { mutableIntStateOf(1) } // 1 para jugador 1, 2 para jugador, 3 para pelear

    //List de cartas seleccionadas
    val cartasJugador1 = remember { mutableStateListOf<Carta>() }
    val cartasJugador2 = remember { mutableStateListOf<Carta>() }


    var resultadoBatalla by remember { mutableStateOf("") }




    if (turnoJugador in 1..2) {

        val cartasSeleccionadasTemporales = remember { mutableStateListOf<Carta>() }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(background_color)
                .padding(top = 100.dp)

        ) {
            item {


                //toggled de todas las cartas
                var isMurcielagoToggled by remember { (mutableStateOf(false)) }
                var isCaballeroToggled by remember { (mutableStateOf(false)) }
                var isArquerasToggled by remember { (mutableStateOf(false)) }
                var isEjercitoEsqueletosToggled by remember { (mutableStateOf(false)) }
                var isValquiriaToggled by remember { (mutableStateOf(false)) }
                var isBrujaToggled by remember { (mutableStateOf(false)) }
                var isBarbarosToggled by remember { (mutableStateOf(false)) }
                var isReinaArqueraToggled by remember { (mutableStateOf(false)) }
                var isMegaCaballeroToggled by remember { (mutableStateOf(false)) }
                var isGiganteNobleToggled by remember { (mutableStateOf(false)) }
                var isPekkaToggled by remember { (mutableStateOf(false)) }
                var isPrincipeToggled by remember { (mutableStateOf(false)) }


                //elixir disponible
                var elixirJugadorActual by remember { (mutableIntStateOf(25)) }


                Text(
                    text = "Elegí tu mazo ¡El jugador con mayor estadísticas gana!\n" +
                            "(no te quedes sin elixir)",
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,

                    modifier = Modifier.padding(10.dp)
                )


                //elixir
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(background_color)
                        .padding(10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(30.dp),
                        colors = CardDefaults.cardColors(containerColor = background_color)

                    ) {
                        Image(
                            painter = painterResource(R.drawable.elixir),
                            contentDescription = "Elixir"
                        )
                    }
                    Text(
                        text = elixirJugadorActual.toString(),
                        color = white,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 5.dp, start = 5.dp)
                    )
                }


                //primera fila de cartas
                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(background_color)
                        .padding(5.dp)
                ) {
                    //murcielago
                    BotonCarta(
                        icon = painterResource(id = R.drawable.murcielagos),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - murcielagos.elixir >= 0 && !isMurcielagoToggled) {
                                elixirJugadorActual -= murcielagos.elixir
                                cartasSeleccionadasTemporales.add(murcielagos)
                                isMurcielagoToggled = true
                            } else if (isMurcielagoToggled) {
                                elixirJugadorActual += murcielagos.elixir
                                cartasSeleccionadasTemporales.remove(murcielagos)
                                isMurcielagoToggled = false
                            }
                        },
                        isToggled = isMurcielagoToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                    //caballero
                    BotonCarta(
                        icon = painterResource(id = R.drawable.caballero),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - caballero.elixir >= 0 && !isCaballeroToggled) {
                                elixirJugadorActual -= caballero.elixir
                                cartasSeleccionadasTemporales.add(caballero)
                                isCaballeroToggled = true
                            } else if (isCaballeroToggled) {
                                elixirJugadorActual += caballero.elixir
                                cartasSeleccionadasTemporales.remove(caballero)
                                isCaballeroToggled = false
                            }
                        },
                        isToggled = isCaballeroToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                    //arqueras
                    BotonCarta(
                        icon = painterResource(id = R.drawable.arqueras),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - arqueras.elixir >= 0 && !isArquerasToggled) {
                                elixirJugadorActual -= arqueras.elixir
                                cartasSeleccionadasTemporales.add(arqueras)
                                isArquerasToggled = true
                            } else if (isArquerasToggled) {
                                elixirJugadorActual += arqueras.elixir
                                cartasSeleccionadasTemporales.remove(arqueras)
                                isArquerasToggled = false
                            }
                        },
                        isToggled = isArquerasToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                    //barbaros
                    BotonCarta(
                        icon = painterResource(id = R.drawable.barbaros),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - barbaros.elixir >= 0 && !isBarbarosToggled) {
                                elixirJugadorActual -= barbaros.elixir
                                cartasSeleccionadasTemporales.add(barbaros)
                                isBarbarosToggled = true
                            } else if (isBarbarosToggled) {
                                elixirJugadorActual += barbaros.elixir
                                cartasSeleccionadasTemporales.remove(barbaros)
                                isBarbarosToggled = false
                            }
                        },
                        isToggled = isBarbarosToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                }

                //segunda fila de cartas
                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(background_color)
                        .padding(5.dp)
                ) {

                    //bruja
                    BotonCarta(
                        icon = painterResource(id = R.drawable.bruja),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - bruja.elixir >= 0 && !isBrujaToggled) {
                                elixirJugadorActual -= bruja.elixir
                                cartasSeleccionadasTemporales.add(bruja)
                                isBrujaToggled = true
                            } else if (isBrujaToggled) {
                                elixirJugadorActual += bruja.elixir
                                cartasSeleccionadasTemporales.remove(bruja)
                                isBrujaToggled = false
                            }
                        },
                        isToggled = isBrujaToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                    //ejercito esqueletos
                    BotonCarta(
                        icon = painterResource(id = R.drawable.ejercito_esqueletos),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - ejercitoEsqueletos.elixir >= 0 && !isEjercitoEsqueletosToggled) {
                                elixirJugadorActual -= ejercitoEsqueletos.elixir
                                cartasSeleccionadasTemporales.add(ejercitoEsqueletos)
                                isEjercitoEsqueletosToggled = true
                            } else if (isEjercitoEsqueletosToggled) {
                                elixirJugadorActual += ejercitoEsqueletos.elixir
                                cartasSeleccionadasTemporales.remove(ejercitoEsqueletos)
                                isEjercitoEsqueletosToggled = false
                            }
                        },
                        isToggled = isEjercitoEsqueletosToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                    //gigante noble
                    BotonCarta(
                        icon = painterResource(id = R.drawable.gigante_noble),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - giganteNoble.elixir >= 0 && !isGiganteNobleToggled) {
                                elixirJugadorActual -= giganteNoble.elixir
                                cartasSeleccionadasTemporales.add(giganteNoble)
                                isGiganteNobleToggled = true
                            } else if (isGiganteNobleToggled) {
                                elixirJugadorActual += giganteNoble.elixir
                                cartasSeleccionadasTemporales.remove(giganteNoble)
                                isGiganteNobleToggled = false
                            }
                        },
                        isToggled = isGiganteNobleToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                    //megacaballero
                    BotonCarta(
                        icon = painterResource(id = R.drawable.megacaballero),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - megacaballero.elixir >= 0 && !isMegaCaballeroToggled) {
                                elixirJugadorActual -= megacaballero.elixir
                                cartasSeleccionadasTemporales.add(megacaballero)
                                isMegaCaballeroToggled = true
                            } else if (isMegaCaballeroToggled) {
                                elixirJugadorActual += megacaballero.elixir
                                cartasSeleccionadasTemporales.remove(megacaballero)
                                isMegaCaballeroToggled = false
                            }
                        },
                        isToggled = isMegaCaballeroToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                }

                //tercera fila de cartas
                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(background_color)
                        .padding(5.dp)
                ) {

                    //reina arquera
                    BotonCarta(
                        icon = painterResource(id = R.drawable.reina_arquera),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - reinaArquera.elixir >= 0 && !isReinaArqueraToggled) {
                                elixirJugadorActual -= reinaArquera.elixir
                                cartasSeleccionadasTemporales.add(reinaArquera)
                                isReinaArqueraToggled = true
                            } else if (isReinaArqueraToggled) {
                                elixirJugadorActual += reinaArquera.elixir
                                cartasSeleccionadasTemporales.remove(reinaArquera)
                                isReinaArqueraToggled = false
                            }
                        },
                        isToggled = isReinaArqueraToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                    //valquiria
                    BotonCarta(
                        icon = painterResource(id = R.drawable.valquiria),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - valquiria.elixir >= 0 && !isValquiriaToggled) {
                                elixirJugadorActual -= valquiria.elixir
                                cartasSeleccionadasTemporales.add(valquiria)
                                isValquiriaToggled = true
                            } else if (isValquiriaToggled) {
                                elixirJugadorActual += valquiria.elixir
                                cartasSeleccionadasTemporales.remove(valquiria)
                                isValquiriaToggled = false
                            }
                        },
                        isToggled = isValquiriaToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                    //principe
                    BotonCarta(
                        icon = painterResource(id = R.drawable.principe),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - principe.elixir >= 0 && !isPrincipeToggled) {
                                elixirJugadorActual -= principe.elixir
                                cartasSeleccionadasTemporales.add(principe)
                                isPrincipeToggled = true
                            } else if (isPrincipeToggled) {
                                elixirJugadorActual += principe.elixir
                                cartasSeleccionadasTemporales.remove(principe)
                                isPrincipeToggled = false
                            }
                        },
                        isToggled = isPrincipeToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                    //pekka
                    BotonCarta(
                        icon = painterResource(id = R.drawable.pekka),
                        activeBackgroundColor = secondary_color,
                        onClick = {

                            if (elixirJugadorActual - pekka.elixir >= 0 && !isPekkaToggled) {
                                elixirJugadorActual -= pekka.elixir
                                cartasSeleccionadasTemporales.add(pekka)
                                isPekkaToggled = true
                            } else if (isPekkaToggled) {
                                elixirJugadorActual += pekka.elixir
                                cartasSeleccionadasTemporales.remove(pekka)
                                isPekkaToggled = false
                            }
                        },
                        isToggled = isPekkaToggled,
                        modifier = Modifier
                            .height(115.dp)
                            .width(90.dp)
                    )

                }


                ElevatedButton(
                    onClick = {
                        if (turnoJugador == 1) {

                            // Guarda las cartas del Jugador 1
                            cartasJugador1.addAll(cartasSeleccionadasTemporales)

                            // Pasa al turno del Jugador 2
                            turnoJugador = 2
                        } else {
                            cartasJugador2.addAll(cartasSeleccionadasTemporales)
                            resultadoBatalla = batalla(cartasJugador1, cartasJugador2)
                            turnoJugador = 3
                        }

                        cartasSeleccionadasTemporales.clear()
                        elixirJugadorActual = 25
                        isMurcielagoToggled = false
                        isCaballeroToggled = false
                        isArquerasToggled = false
                        isEjercitoEsqueletosToggled = false
                        isValquiriaToggled = false
                        isBrujaToggled = false
                        isBarbarosToggled = false
                        isReinaArqueraToggled = false
                        isMegaCaballeroToggled = false
                        isGiganteNobleToggled = false
                        isPrincipeToggled = false
                        isPekkaToggled = false

                    },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = secondary_color,
                        contentColor = card_background_color
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, end = 15.dp, start = 15.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = if (turnoJugador == 1) "Jugador 1: Listo" else "Jugador 2: Listo",
                        fontSize = 20.sp
                    )
                }

            }
        }
    }


    //si no le toca a nadie es pq eligieron los 2
    //toca la pelea
    if (turnoJugador == 3) {



        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(background_color)
                .padding(10.dp, top = 100.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = resultadoBatalla,
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 5.dp, start = 5.dp)
                )

                ElevatedButton(
                    onClick = {

                        turnoJugador = 1
                        cartasJugador1.clear()
                        cartasJugador2.clear()
                        resultadoBatalla = ""

                    },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = secondary_color,
                        contentColor = card_background_color
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, end = 15.dp, start = 15.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Volver a Jugar",
                        fontSize = 20.sp
                    )
                }
            }

        }
    }
}