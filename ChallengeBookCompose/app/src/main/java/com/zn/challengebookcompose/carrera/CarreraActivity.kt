package com.zn.challengebookcompose.carrera

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.R
import com.zn.challengebookcompose.carrera.componentes.BotonCorrerSaltar
import com.zn.challengebookcompose.carrera.componentes.CardCarrera
import com.zn.challengebookcompose.conjuntos.ConjuntosActivity
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BotonMenu
import com.zn.challengebookcompose.ui.theme.componentesgenerales.TextoNormal
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white
import kotlin.random.Random

class CarreraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewContainer()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun ViewContainer() {
    val context = LocalContext.current
    Scaffold(
        topBar = { BarraSuperior("Carrera de obstáculos", card_background_color) },
        content = {
            ContentCarrera(onConjuntos = { context.startActivity(Intent(context, ConjuntosActivity::class.java)) })
        }
    )
}

@Composable
fun ContentCarrera(
    onConjuntos: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(top = 110.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            TextoNormal(
                "Salta o Corre en base a las imágenes!",
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )

            var resultadoRandomImg by remember { mutableIntStateOf(Random.nextInt(0, 2)) }
            var counter by remember { mutableIntStateOf(0) }
            var perdio by remember { mutableStateOf(false) }
            var animationKey by remember { mutableIntStateOf(0) }

            val resultadoAnimado = Pair(animationKey, resultadoRandomImg)

            if (counter < 5 && !perdio) {

                AnimatedContent(
                    targetState = resultadoAnimado,
                    label = "Image Animation",
                    transitionSpec = {
                        (fadeIn() + slideInVertically()).togetherWith(fadeOut() + slideOutVertically())
                    }
                ) { (key, targetImg) ->
                    CardCarrera(random = targetImg)
                }

                // Muestra los botones de correr y saltar
                Row(
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(background_color)
                        .padding(20.dp)
                ) {
                    // Botón correr
                    BotonCorrerSaltar(
                        icon = painterResource(R.drawable.correr),
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        onClick = {
                            if (ganoPerdio(resultadoRandomImg, 0)) {
                                perdio = true
                            } else {
                                resultadoRandomImg = Random.nextInt(0, 2)
                                counter++
                                animationKey++
                            }
                        }
                    )

                    // Botón saltar
                    BotonCorrerSaltar(
                        icon = painterResource(R.drawable.saltar),
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        onClick = {
                            if (ganoPerdio(resultadoRandomImg, 1)) {
                                perdio = true
                            } else {
                                resultadoRandomImg = Random.nextInt(0, 2)
                                counter++
                                animationKey++
                            }
                        }
                    )
                }

            } else {
                // El juego ha terminado, mostramos el resultado
                val resultado = if (perdio) "Perdiste :(" else "Ganaste!!"

                Text(
                    text = resultado,
                    color = white,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 40.dp)
                )

                ElevatedButton(onClick = {
                    counter = 0
                    perdio = false
                    resultadoRandomImg = Random.nextInt(0, 2)
                                         },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = secondary_color,
                        contentColor = card_background_color
                    ),
                    modifier = Modifier.padding(top = 20.dp, end = 15.dp, start = 15.dp)
                        .fillMaxWidth()){
                    Text("Volver a jugar")
                }

                BotonMenu(
                    texto = "Siguiente Ejercicio",
                    color = card_background_color,
                    onClick = onConjuntos,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 40.dp),
                )
            }
        }
    }
}

fun ganoPerdio(random: Int, eleccion: Int): Boolean {

    //si no coinciden perdió
    return random != eleccion

}
