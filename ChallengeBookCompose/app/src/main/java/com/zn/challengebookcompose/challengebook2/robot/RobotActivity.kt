package com.zn.challengebookcompose.challengebook2.robot

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zn.challengebookcompose.R
import com.zn.challengebookcompose.challengebook2.Menu2Activity
import com.zn.challengebookcompose.ui.theme.background_color
import com.zn.challengebookcompose.ui.theme.card_background_color
import com.zn.challengebookcompose.ui.theme.componentesgenerales.BarraSuperior
import com.zn.challengebookcompose.ui.theme.secondary_color
import com.zn.challengebookcompose.ui.theme.white
import kotlin.random.Random

class RobotActivity : ComponentActivity() {
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
            BarraSuperior("Robot", card_background_color, onHomeClick = {
                val intent = Intent(context, Menu2Activity::class.java)
                context.startActivity(intent)
            })
        },
        content = {
            ContentRobot(
            )
        }
    )
}

@Composable
fun ContentRobot() {
    val tamanioPlanoDp = 300.dp
    val tamanioPlanoPx = 300f


    val robotSizeDp = 40.dp
    val metaSizeDp = 10.dp

    var posX by remember { mutableFloatStateOf(0f) }
    var posY by remember { mutableFloatStateOf(0f) }
    var inputX by remember { mutableStateOf("") }
    var inputY by remember { mutableStateOf("") }

    var posXMeta by remember { mutableIntStateOf(Random.nextInt(0, 15)) }
    var posYMeta by remember { mutableIntStateOf(Random.nextInt(0, 15)) }

    var mostrarAlerta by remember { mutableStateOf(false) }
    var mostrarPista by remember { mutableStateOf(false) }
    var mostrarGanador by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .padding(top = 80.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "¿Dónde está el robot?",
            color = white,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
        // plano cartesiano
        Box(
            modifier = Modifier
                .size(tamanioPlanoDp)
                .background(Color.White)
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                val midX = size.width / 2
                val midY = size.height / 2

                // eje x
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, midY),
                    end = Offset(size.width, midY),
                    strokeWidth = 5f
                )

                // eje y
                drawLine(
                    color = Color.Gray,
                    start = Offset(midX, 0f),
                    end = Offset(midX, size.height),
                    strokeWidth = 5f
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_robot),
                contentDescription = "Robot",
                modifier = Modifier
                    .offset(
                        x = ((tamanioPlanoPx / 2 + posX).dp - robotSizeDp / 2),
                        y = ((tamanioPlanoPx / 2 - posY).dp - robotSizeDp / 2)
                    )
                    .size(robotSizeDp)
            )




            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = "Meta",
                modifier = Modifier
                    .offset(
                        x = ((tamanioPlanoPx / 2 + posXMeta.toFloat() * 10f).dp - metaSizeDp / 2),
                        y = ((tamanioPlanoPx / 2 - posYMeta.toFloat() * 10f).dp - metaSizeDp / 2)
                    )
                    .size(metaSizeDp)
            )


        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            OutlinedTextField(
                value = inputX,
                onValueChange = { inputX = it },
                label = { Text("X") },
                modifier = Modifier
                    .width(145.dp)
                    .padding(top = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = white,
                    unfocusedTextColor = white,
                    cursorColor = white,
                    focusedBorderColor = white,
                    unfocusedBorderColor = white,
                    focusedLabelColor = white,
                    unfocusedLabelColor = white,
                )
            )


            OutlinedTextField(
                value = inputY,
                onValueChange = { inputY = it },
                label = { Text("Y") },
                modifier = Modifier
                    .width(145.dp)
                    .padding(top = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = white,
                    unfocusedTextColor = white,
                    cursorColor = white,
                    focusedBorderColor = white,
                    unfocusedBorderColor = white,
                    focusedLabelColor = white,
                    unfocusedLabelColor = white,
                )
            )

        }


        ElevatedButton(
            onClick = {

                if (inputX.toInt() >= 15 || inputY.toInt() >= 15) {
                    mostrarAlerta = true
                }else {
                    val x = inputX.toFloatOrNull() ?: 0f
                    val y = inputY.toFloatOrNull() ?: 0f
                    posX = x * 10f
                    posY = y * 10f


                    val robotX = x.toInt()
                    val robotY = y.toInt()

                    if (robotX == posXMeta && robotY == posYMeta){
                        mostrarGanador = true
                    }

                }

            },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = secondary_color,
                contentColor = card_background_color
            ),
            modifier = Modifier
                .padding(top = 30.dp, end = 30.dp, start = 30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Mover",
                fontSize = 20.sp
            )
        }

        ElevatedButton(
            onClick = {
                mostrarPista = true
            },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = secondary_color,
                contentColor = card_background_color
            ),
            modifier = Modifier
                .padding(top = 30.dp, end = 30.dp, start = 30.dp)
        ) {
            Text(
                text = "Pista",
                fontSize = 20.sp
            )
        }


        if (mostrarAlerta) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(text = "Error") },
                text = { Text("Ingresar solo valores menores a 15") },
                confirmButton = {
                    Button(onClick = { mostrarAlerta = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }

        if (mostrarPista) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(text = "Pista") },
                text = { Text("La meta está en x: $posXMeta, y: $posYMeta") },
                confirmButton = {
                    Button(onClick = { mostrarPista = false }) {
                        Text("Gracias!")
                    }
                }
            )
        }

        if (mostrarGanador) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(text = "Ganaste!") },
                text = { Text("Llegaste a la meta!") },
                confirmButton = {
                    Button(onClick = {
                        posXMeta = Random.nextInt(0, 15)
                        posYMeta = Random.nextInt(0, 15)

                        posY = 0f
                        posX = 0f

                        mostrarGanador = false
                    }) {
                        Text("Jugar otra vez")
                    }
                }
            )
        }



    }
}