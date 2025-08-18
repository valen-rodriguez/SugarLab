package com.zn.challengebookcompose.challengebook2.truco

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.challengebook2.Menu2Activity
import com.zn.challengebookcompose.challengebook2.truco.componentes.Carta
import com.zn.challengebookcompose.challengebook2.truco.componentes.CartaMemoria
import com.zn.challengebookcompose.challengebook2.truco.componentes.generarCartas
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.white
import kotlinx.coroutines.delay

class TratoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewContainer()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
private fun ViewContainer() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            BarraSuperior("Truco O Trato", card_background_color, onHomeClick = {
                val intent = Intent(context, Menu2Activity::class.java)
                context.startActivity(intent)
            })
        },
        content = {
            ContentTrucoOTrato(
//            onAnagrama = { context.startActivity(Intent(context, AnagramaActivity::class.java)) }
            )
        }
    )
}

@Composable
fun ContentTrucoOTrato() {

    var cartas by remember { mutableStateOf(generarCartas()) }
    var seleccionadas by remember { mutableStateOf<List<Carta>>(emptyList()) }

    val juegoGanado = cartas.all { it.encontrada }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(top = 80.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "¡Memo Test!",
            color = white,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        if (juegoGanado) {
            // 🎉 Mensaje de victoria
            Text(
                text = "¡Ganaste! 🎉",
                color = white,
                fontSize = 26.sp,
                modifier = Modifier.padding(20.dp)
            )

            LaunchedEffect(Unit) {
                delay(2000)
                cartas = generarCartas()
            }

        } else {

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {

                items(cartas.size) { index ->
                    CartaMemoria(
                        carta = cartas[index],
                        onClick = {
                            if (!cartas[index].revelada && seleccionadas.size < 2) {
                                cartas = cartas.map {
                                    if (it.id == cartas[index].id) it.copy(revelada = true)
                                    else it
                                }
                                seleccionadas = seleccionadas + cartas[index]
                            }
                        }
                    )
                }
            }
        }


        LaunchedEffect(seleccionadas) {
            if (seleccionadas.size == 2) {
                delay(800)
                val (c1, c2) = seleccionadas
                cartas = if (c1.contenido == c2.contenido) {
                    cartas.map {
                        if (it.id == c1.id || it.id == c2.id) it.copy(encontrada = true)
                        else it
                    }
                } else {
                    cartas.map {
                        if (it.id == c1.id || it.id == c2.id) it.copy(revelada = false)
                        else it
                    }
                }
                seleccionadas = emptyList()
            }
        }

    }
}

