package com.zn.challengebookcompose.rps

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.zn.challengebookcompose.MainActivity
import com.zn.challengebookcompose.R
import com.zn.challengebookcompose.batalla.BatallaActivity
import com.zn.challengebookcompose.rps.componentes.BotonEleccion
import com.zn.challengebookcompose.rps.componentes.quienGana
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BotonMenu
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white

class RpsActivity : ComponentActivity() {
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
            BarraSuperior("Piedra, Papel o Tijeras", card_background_color, onHomeClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            })
        },
        content = { ContentRps(
            onBatalla = { context.startActivity(Intent(context, BatallaActivity::class.java))}
            ) }
    )
}

@Composable
private fun ContentRps(
    onBatalla:() -> Unit
) {

    var player1Selection by remember { mutableStateOf("R") }
    var player2Selection by remember { mutableStateOf("R") }
    var ganador by remember { mutableStateOf("") }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(10.dp, top = 100.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {

            Text(
                text = "Jugador 1:",
                color = white,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )


            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(background_color)
                    .padding(10.dp)
            ) {

//                --------- Jugador 1 ---------

                //piedra
                BotonEleccion(
                    icon = painterResource(R.drawable.ic_piedra),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    isToggled = player1Selection == "R",
                    onClick = {
                        player1Selection = "R"
                        ganador = ""
                    }
                )

                //papel
                BotonEleccion(
                    icon = painterResource(R.drawable.ic_paper),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    isToggled = player1Selection == "P",
                    onClick = {
                        player1Selection = "P"
                        ganador = ""
                    }
                )


                //tijera
                BotonEleccion(
                    icon = painterResource(R.drawable.ic_scissors),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    isToggled = player1Selection == "S",
                    onClick = {
                        player1Selection = "S"
                        ganador = ""
                    }
                )
            }


            //                --------- Jugador 2 ---------

            Text(
                text = "Jugador 2:",
                color = white,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )

            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(background_color)
                    .padding(10.dp)
            ) {


                //piedra
                BotonEleccion(
                    icon = painterResource(R.drawable.ic_piedra),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    isToggled = player2Selection == "R",
                    onClick = {
                        player2Selection = "R"
                        ganador = ""
                    }
                )

                //papel
                BotonEleccion(
                    icon = painterResource(R.drawable.ic_paper),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    isToggled = player2Selection == "P",
                    onClick = {
                        player2Selection = "P"
                        ganador = ""
                    }
                )


                //tijera
                BotonEleccion(
                    icon = painterResource(R.drawable.ic_scissors),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    isToggled = player2Selection == "S",
                    onClick = {
                        player2Selection = "S"
                        ganador = ""
                    }
                )
            }



            ElevatedButton(
                onClick = {
                    ganador = quienGana(player1Selection, player2Selection)
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = secondary_color,
                    contentColor = card_background_color
                ),
                modifier = Modifier
                    .padding(top = 20.dp, end = 15.dp, start = 15.dp)
                    .fillMaxWidth()
            ) {
                Text("Jugar")
            }

            Text(
                text = ganador,
                color = white,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 30.dp)
            )

            BotonMenu(
                texto = "Siguiente Ejercicio",
                color = card_background_color,
                onClick = onBatalla,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 40.dp),
            )

        }

    }
}


