package com.zn.challengebookcompose.challengebook2.iterationmaster

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.challengebook2.Menu2Activity
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IterationActivity : ComponentActivity() {
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
            BarraSuperior("Iteration master", card_background_color, onHomeClick = {
                val intent = Intent(context, Menu2Activity::class.java)
                context.startActivity(intent)
            })
        },
        content = {
            ContentIteration(
//            onAnagrama = { context.startActivity(Intent(context, AnagramaActivity::class.java)) }
            )
        }
    )
}

@Composable
fun ContentIteration() {

    var numeroFor by remember { mutableStateOf("") }
    var numeroWhile by remember { mutableStateOf("") }
    var numeroDoWhile by remember { mutableStateOf("") }
    var numeroRecursion by remember { mutableStateOf("") }

    var tiempoFor by remember { mutableStateOf("") }
    var tiempoWhile by remember { mutableStateOf("") }
    var tiempoDoWhile by remember { mutableStateOf("") }
    var tiempoRecursion by remember { mutableStateOf("") }


    var empezar by remember {mutableStateOf(false)}



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(top = 110.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            Text(
                text = "Veamos que método es más rápido",
                color = white,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            ElevatedButton(
                onClick = {
                    empezar = false

                    numeroFor = ""
                    numeroWhile = ""
                    numeroDoWhile = ""
                    numeroRecursion = ""

                    tiempoFor = ""
                    tiempoWhile = ""
                    tiempoDoWhile = ""
                    tiempoRecursion = ""

                    empezar = true
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = secondary_color,
                    contentColor = card_background_color
                ),
                modifier = Modifier
                    .padding(end = 15.dp, start = 15.dp, bottom = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Calcular tiempo",
                    fontSize = 15.sp
                )
            }


            if (empezar) {
                LaunchedEffect(empezar) {
                    // FOR
                    launch {
                        for (i in 1..30) {
                            numeroFor = i.toString()
                            delay(150)
                        }
                        tiempoFor = (150*30).toString() + "ms"
                    }

                    // WHILE
                    launch {
                        var j = 1
                        while (j <= 30) {
                            numeroWhile = j.toString()
                            delay(150)
                            j++
                        }
                        tiempoWhile = (150*30).toString() + "ms"
                    }

                    // DO-WHILE
                    launch {
                        var k = 1
                        do {
                            numeroDoWhile = k.toString()
                            delay(160)
                            k++
                        } while (k <= 30)
                        tiempoDoWhile = (160*30).toString() + "ms"
                    }

                    // RECURSION
                    launch {
                        suspend fun contarRecursivo(n: Int) {
                            if (n > 30) return
                            numeroRecursion = n.toString()
                            delay(250)
                            contarRecursivo(n + 1)
                        }
                        contarRecursivo(1)
                        tiempoRecursion = (200*30).toString() + "ms"
                        empezar = false
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(background_color)
                    .padding(5.dp)
            ) {

                //for
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(card_background_color)
                        .padding(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = card_background_color),
                        modifier = Modifier.size(width = 150.dp, 180.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(card_background_color)
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally

                        )
                        {
                            Text(
                                text = "Contador For",
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                            Text(
                                text = numeroFor,
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 30.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp,  top = 40.dp, bottom = 5.dp)
                            )

                            Text(
                                text = tiempoFor,
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                //while
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(card_background_color)
                        .padding(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = card_background_color),
                        modifier = Modifier.size(width = 150.dp, 180.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(card_background_color)
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally

                        )
                        {

                            Text(
                                text = "Contador While",
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                            Text(
                                text = numeroWhile,
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 30.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp, top = 20.dp)
                            )
                            Text(
                                text = tiempoWhile,
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(background_color)
                    .padding(horizontal = 5.dp, vertical = 20.dp)
            ) {

                //do while
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(card_background_color)
                        .padding(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = card_background_color),
                        modifier = Modifier.size(width = 150.dp, 180.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(card_background_color)
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally

                        )
                        {
                            Text(
                                text = "Contador Do-While",
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                            Text(
                                text = numeroDoWhile,
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 30.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 10.dp, start = 10.dp, top = 30.dp, bottom = 5.dp)
                            )

                            Text(
                                text = tiempoDoWhile,
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                //recursion
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(card_background_color)
                        .padding(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = card_background_color),
                        modifier = Modifier.size(width = 150.dp, 180.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(card_background_color)
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally

                        )
                        {

                            Text(
                                text = "Contador Recursion",
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                            Text(
                                text = numeroRecursion,
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 30.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 5.dp)
                            )

                            Text(
                                text = tiempoRecursion,
                                color = white,
                                fontFamily = FontFamily.Monospace,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

